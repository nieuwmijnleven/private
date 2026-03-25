/*
 * JADEx - Java Advanced Development Extension
 *
 * Copyright (C) 2026 Cheol Jeon <nieuwmijnleven@outlook.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 only,
 * as published by the Free Software Foundation.
 *
 * Alternatively, this software may be used under a commercial license
 * from Cheol Jeon.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License version 2 for more details:
 * <https://www.gnu.org/licenses/old-licenses/gpl-2.0.html>.
 *
 * For commercial licensing, please contact <nieuwmijnleven@outlook.com>.
 *
 * Contributors to this project must sign a Contributor License Agreement (CLA)
 * granting Cheol Jeon the right to relicense their contributions under
 * a commercial license. See the CLA file in the project root for details.
 */

package jplus.generator;

import jplus.base.JADExParser;
import jplus.base.JADExParser.TypeTypeContext;
import jplus.base.JADExParserBaseVisitor;
import jplus.generator.context.adapter.TypeContextAdapter;
import jplus.util.Utils;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.RuleNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JADExBasicCodeGenerator extends JADExParserBaseVisitor<Void> {

    private static final Logger log = LoggerFactory.getLogger(JADExBasicCodeGenerator.class);

    private static final Void NOTHING = null;

    private final TokenStreamRewriter rewriter;

    public JADExBasicCodeGenerator(TokenStream tokens) {
        this.rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    protected void afterVisit(RuleNode node, Void result) {
        postProcess(node, result);
    }

    private void postProcess(RuleNode node, Void result) {

    }

    // replace nullable mark (?)

    @Override
    public Void visitTypeType(TypeTypeContext ctx) {
        replaceNullType(TypeContextAdapter.from(ctx));
        return NOTHING;
    }

    @Override
    public Void visitResource(JADExParser.ResourceContext ctx) {
        replaceNullType(TypeContextAdapter.from(ctx));
        return NOTHING;
    }

    protected void replaceNullType(TypeContextAdapter ctx) {

        if (ctx.QUESTION() == null) return;

        boolean hasNullableAnnotation =
                ctx.annotation().stream()
                        .anyMatch(anno -> {
                            String text = anno.getText();
                            return text.equals("@Nullable")
                                    || text.endsWith(".Nullable");
                        });

        rewriter.delete(ctx.QUESTION().getSymbol());

        if (!hasNullableAnnotation) {
            rewriter.insertBefore(ctx.classOrInterfaceType().start, "@org.jspecify.annotations.Nullable ");
        }
    }

    // replace ?. chaining with SafeAccess

    @Override
    public Void visitMemberReferenceExpression(JADExParser.MemberReferenceExpressionContext ctx) {
        super.visitChildren(ctx);

        if (isChainRoot(ctx)) {
            if (containsSafeAccess(ctx)) {
                System.out.println("containsSafeAccess  = " + Utils.getTokenString(ctx));
                String transformed = transformToSafeAccess(ctx, ".orElse(null)");
                System.out.println("transformed  = " + transformed);
                rewriter.replace(ctx.start, ctx.stop, transformed);
                return NOTHING;
            }
        }

        return NOTHING;
    }

    private boolean isChainRoot(JADExParser.MemberReferenceExpressionContext ctx) {
        return !(ctx.getParent() instanceof JADExParser.MemberReferenceExpressionContext);
    }

    private boolean containsSafeAccess(JADExParser.ExpressionContext ctx) {

        int startIndex = ctx.start.getTokenIndex();
        int stopIndex = ctx.stop.getTokenIndex();

        for (int i = startIndex; i <= stopIndex; i++) {
            Token token = rewriter.getTokenStream().get(i);
            if (token.getType() == JADExParser.NULLSAFE) {
                return true;
            }
        }
        return false;
    }



    private String transformToSafeAccess(JADExParser.MemberReferenceExpressionContext ctx, String terminalOp) {
        List<AccessPart> parts = new ArrayList<>();
        JADExParser.ExpressionContext current = ctx;

        while (current instanceof JADExParser.MemberReferenceExpressionContext mref) {

            String op = mref.bop.getText();
            String member = rewriter.getText(mref.getChild(2).getSourceInterval());

            parts.add(0, new AccessPart(op, member));

            current = mref.expression();
        }

        String rootText = rewriter.getText(current.getSourceInterval());
        System.out.println("rootText = " + rootText);

        StringBuilder sb = new StringBuilder();
        sb.append("jadex.runtime.SafeAccess.ofNullable(").append(rootText).append(")");

        int varIdx = 0;
        for (int i = 0; i < parts.size(); i++) {
            AccessPart part = parts.get(i);

            if (part.op.equals("?.")) {
                String tVar = "t" + (varIdx++);
                sb.append(".map(").append(tVar).append(" -> ").append(tVar).append(".").append(part.member);

                while (i + 1 < parts.size() && parts.get(i + 1).op.equals(".")) {
                    i++;
                    sb.append(".").append(parts.get(i).member);
                }
                sb.append(")");
            } else {
                int insertPos = sb.lastIndexOf(")");
                sb.insert(insertPos, "." + part.member);
            }
        }

        sb.append(terminalOp);
        return sb.toString();
    }

    private static class AccessPart {
        final String op;
        final String member;
        AccessPart(String op, String member) { this.op = op; this.member = member; }
    }

    @Override
    public Void visitElvisExpression(JADExParser.ElvisExpressionContext ctx) {

        super.visitChildren(ctx);

        JADExParser.ExpressionContext lhs = ctx.expression(0);
        JADExParser.ExpressionContext rhs = ctx.expression(1);

        // 1. 오른쪽(fallback) 텍스트 추출
        String fallbackText = rewriter.getText(rhs.getSourceInterval());
        String replacement;

        JADExParser.MemberReferenceExpressionContext topMRef = findTopMemberRef(lhs);

        if (topMRef != null && containsSafeAccess(lhs)) {
            /* Case 1: LHS에 ?. 연산자가 포함된 경우 */
            // MemberReferenceExpression 방문자에게 맡기되,
            // 마지막 .orElse(null) 대신 .orElseGet(() -> fallback)을 쓰도록 유도해야 함.
            // 이를 위해 transformToSafeAccess 메서드를 수정하거나, 결과 문자열의 끝을 치환합니다.

            replacement = transformToSafeAccess(topMRef, String.format(".orElseGet(() -> %s)", fallbackText));

        } else {
            /* Case 2: 일반적인 식인 경우 */
            String lhsText = rewriter.getText(lhs.getSourceInterval());
            replacement = String.format("SafeAccess.ofNullable(%s).orElseGet(() -> %s)",
                    lhsText, fallbackText);
        }

        // 2. 전체 엘비스 식을 변환된 코드로 치환
        rewriter.replace(ctx.start, ctx.stop, replacement);

        return NOTHING; // 자식 노드 개별 방문 방지
    }

    /**
     * LHS 내부에서 가장 바깥쪽 MemberReferenceExpression을 찾음
     */
    private JADExParser.MemberReferenceExpressionContext findTopMemberRef(ParserRuleContext ctx) {
        if (ctx instanceof JADExParser.MemberReferenceExpressionContext mref) {
            return mref;
        }
        // 괄호 (expression) 같은 경우 자식 노드 중 Expression을 따라 내려감
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i) instanceof JADExParser.ExpressionContext child) {
                JADExParser.MemberReferenceExpressionContext found = findTopMemberRef(child);
                if (found != null) return found;
            }
        }
        return null;
    }

    public String getText() {
        return rewriter.getText();
    }
}
