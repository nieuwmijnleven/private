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

package jplus.base;

import org.antlr.v4.runtime.*;

import java.util.List;

public abstract class JADExParserBase extends Parser {

    public JADExParserBase(TokenStream input){
	super(input);
    }

    public boolean DoLastRecordComponent() {
	ParserRuleContext ctx = this.getContext();
	if (!(ctx instanceof JADExParser.RecordComponentListContext)) {
	    return true; // or throw if this is an unexpected state
	}

	JADExParser.RecordComponentListContext tctx = (JADExParser.RecordComponentListContext) ctx;
	List<JADExParser.RecordComponentContext> rcs = tctx.recordComponent();
	if (rcs.isEmpty()) return true;

	int count = rcs.size();
	for (int c = 0; c < count; ++c) {
	    JADExParser.RecordComponentContext rc = rcs.get(c);
	    if (rc.ELLIPSIS() != null && c + 1 < count)
		return false;
	}
	return true;
    }

    public boolean IsNotIdentifierAssign()
    {
	var la = this._input.LA(1);
	    // If not identifier, return true because it can't be
	    // "identifier = ..."
	switch (la) {
	    case JADExParser.IDENTIFIER:
	    case JADExParser.MODULE:
	    case JADExParser.OPEN:
	    case JADExParser.REQUIRES:
	    case JADExParser.EXPORTS:
	    case JADExParser.OPENS:
	    case JADExParser.TO:
	    case JADExParser.USES:
	    case JADExParser.PROVIDES:
	    case JADExParser.WHEN:
	    case JADExParser.WITH:
	    case JADExParser.TRANSITIVE:
	    case JADExParser.YIELD:
	    case JADExParser.SEALED:
	    case JADExParser.PERMITS:
	    case JADExParser.RECORD:
	    case JADExParser.VAR:
		break;
	    default:
		return true;
	}
	var la2 = this._input.LA(2);
	if (la2 != JADExParser.ASSIGN) return true;
	return false;
    }
}
