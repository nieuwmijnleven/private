package jplus.plugin.intellij.adapter;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import jplus.base.JPlus25Parser;
import jplus.plugin.intellij.JPlusLanguage;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

public class JPlusParserAdapter extends ANTLRParserAdaptor {

    public JPlusParserAdapter() {
        super(JPlusLanguage.INSTANCE, new JPlus25Parser(null));
    }

    @Override
    protected ParseTree parse(Parser parser, IElementType root) {
        if (root instanceof IFileElementType) {
            //parser.setBuildParseTree(false);
            return ((JPlus25Parser) parser).start_();
        }

        throw new UnsupportedOperationException(String.format("cannot start parsing using root element %s", root));
    }
}
