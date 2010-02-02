package com.puresol.coding.lang.cpp.source.tokengroups;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.cpp.source.symbols.Assign;
import com.puresol.coding.lang.cpp.source.symbols.BackSlash;
import com.puresol.coding.lang.cpp.source.symbols.BitAnd;
import com.puresol.coding.lang.cpp.source.symbols.BitOr;
import com.puresol.coding.lang.cpp.source.symbols.Caret;
import com.puresol.coding.lang.cpp.source.symbols.Colon;
import com.puresol.coding.lang.cpp.source.symbols.Comma;
import com.puresol.coding.lang.cpp.source.symbols.Dot;
import com.puresol.coding.lang.cpp.source.symbols.Equal;
import com.puresol.coding.lang.cpp.source.symbols.GreaterEqual;
import com.puresol.coding.lang.cpp.source.symbols.GreaterThan;
import com.puresol.coding.lang.cpp.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.cpp.source.symbols.LParen;
import com.puresol.coding.lang.cpp.source.symbols.LRectBracket;
import com.puresol.coding.lang.cpp.source.symbols.LessEqual;
import com.puresol.coding.lang.cpp.source.symbols.LessThan;
import com.puresol.coding.lang.cpp.source.symbols.LineBreak;
import com.puresol.coding.lang.cpp.source.symbols.LineComment;
import com.puresol.coding.lang.cpp.source.symbols.LogicalAnd;
import com.puresol.coding.lang.cpp.source.symbols.LogicalOr;
import com.puresol.coding.lang.cpp.source.symbols.Minus;
import com.puresol.coding.lang.cpp.source.symbols.MinusAssign;
import com.puresol.coding.lang.cpp.source.symbols.MinusMinus;
import com.puresol.coding.lang.cpp.source.symbols.MultiLineComment;
import com.puresol.coding.lang.cpp.source.symbols.Not;
import com.puresol.coding.lang.cpp.source.symbols.Percent;
import com.puresol.coding.lang.cpp.source.symbols.Plus;
import com.puresol.coding.lang.cpp.source.symbols.PlusAssign;
import com.puresol.coding.lang.cpp.source.symbols.PlusPlus;
import com.puresol.coding.lang.cpp.source.symbols.QuestionMark;
import com.puresol.coding.lang.cpp.source.symbols.RCurlyBracket;
import com.puresol.coding.lang.cpp.source.symbols.RParen;
import com.puresol.coding.lang.cpp.source.symbols.RRectBracket;
import com.puresol.coding.lang.cpp.source.symbols.Semicolon;
import com.puresol.coding.lang.cpp.source.symbols.Slash;
import com.puresol.coding.lang.cpp.source.symbols.SlashAssign;
import com.puresol.coding.lang.cpp.source.symbols.Star;
import com.puresol.coding.lang.cpp.source.symbols.StarAssign;
import com.puresol.coding.lang.cpp.source.symbols.Tilde;
import com.puresol.coding.lang.cpp.source.symbols.Unequal;
import com.puresol.coding.lang.cpp.source.symbols.WhiteSpace;
import com.puresol.parser.AbstractTokenDefinitionGroup;
import com.puresol.parser.TokenException;

public class CPPSymbols extends AbstractTokenDefinitionGroup {

    private static final Logger logger =
	    Logger.getLogger(CPPSymbols.class);

    @Override
    protected void initialize() {
	try {
	    addTokenDefinition(MultiLineComment.class);
	    addTokenDefinition(LineComment.class);
	    addTokenDefinition(LineBreak.class);
	    addTokenDefinition(WhiteSpace.class);

	    addTokenDefinition(LParen.class);
	    addTokenDefinition(RParen.class);
	    addTokenDefinition(LCurlyBracket.class);
	    addTokenDefinition(RCurlyBracket.class);
	    addTokenDefinition(LRectBracket.class);
	    addTokenDefinition(RRectBracket.class);

	    addTokenDefinition(LessEqual.class);
	    addTokenDefinition(GreaterEqual.class);
	    addTokenDefinition(LessThan.class);
	    addTokenDefinition(GreaterThan.class);
	    addTokenDefinition(Equal.class);
	    addTokenDefinition(Unequal.class);
	    addTokenDefinition(PlusAssign.class);
	    addTokenDefinition(MinusAssign.class);
	    addTokenDefinition(SlashAssign.class);
	    addTokenDefinition(StarAssign.class);
	    addTokenDefinition(Assign.class);
	    addTokenDefinition(BitOr.class);
	    addTokenDefinition(Caret.class);

	    addTokenDefinition(PlusPlus.class);
	    addTokenDefinition(MinusMinus.class);

	    addTokenDefinition(Plus.class);
	    addTokenDefinition(Minus.class);
	    addTokenDefinition(Slash.class);
	    addTokenDefinition(Star.class);
	    addTokenDefinition(Percent.class);
	    addTokenDefinition(BackSlash.class);

	    addTokenDefinition(LogicalAnd.class);
	    addTokenDefinition(BitAnd.class);
	    addTokenDefinition(LogicalOr.class);
	    addTokenDefinition(BitOr.class);
	    addTokenDefinition(Not.class);

	    addTokenDefinition(Tilde.class);
	    addTokenDefinition(QuestionMark.class);

	    addTokenDefinition(Semicolon.class);
	    addTokenDefinition(Comma.class);
	    addTokenDefinition(Colon.class);
	    addTokenDefinition(Dot.class);
	} catch (TokenException e) {
	    logger.error(e.getMessage());
	}
    }
}
