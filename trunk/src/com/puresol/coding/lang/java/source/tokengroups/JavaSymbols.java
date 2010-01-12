package com.puresol.coding.lang.java.source.tokengroups;

import com.puresol.coding.lang.java.source.symbols.Assign;
import com.puresol.coding.lang.java.source.symbols.At;
import com.puresol.coding.lang.java.source.symbols.BitAnd;
import com.puresol.coding.lang.java.source.symbols.BitOr;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.Equal;
import com.puresol.coding.lang.java.source.symbols.GreaterEqual;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.LessEqual;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.coding.lang.java.source.symbols.LineBreak;
import com.puresol.coding.lang.java.source.symbols.LineComment;
import com.puresol.coding.lang.java.source.symbols.LogicalAnd;
import com.puresol.coding.lang.java.source.symbols.LogicalOr;
import com.puresol.coding.lang.java.source.symbols.Minus;
import com.puresol.coding.lang.java.source.symbols.MinusMinus;
import com.puresol.coding.lang.java.source.symbols.MultiLineComment;
import com.puresol.coding.lang.java.source.symbols.Not;
import com.puresol.coding.lang.java.source.symbols.Percent;
import com.puresol.coding.lang.java.source.symbols.Plus;
import com.puresol.coding.lang.java.source.symbols.PlusAssign;
import com.puresol.coding.lang.java.source.symbols.PlusPlus;
import com.puresol.coding.lang.java.source.symbols.QuestionMark;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.coding.lang.java.source.symbols.Slash;
import com.puresol.coding.lang.java.source.symbols.Star;
import com.puresol.coding.lang.java.source.symbols.Unequal;
import com.puresol.coding.lang.java.source.symbols.WhiteSpace;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class JavaSymbols extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {
	addKeyword(MultiLineComment.class);
	addKeyword(LineComment.class);
	addKeyword(LineBreak.class);
	addKeyword(WhiteSpace.class);

	addKeyword(LParen.class);
	addKeyword(RParen.class);
	addKeyword(LCurlyBracket.class);
	addKeyword(RCurlyBracket.class);
	addKeyword(LRectBracket.class);
	addKeyword(RRectBracket.class);

	addKeyword(LessEqual.class);
	addKeyword(GreaterEqual.class);
	addKeyword(LessThan.class);
	addKeyword(GreaterThan.class);
	addKeyword(Equal.class);
	addKeyword(Unequal.class);
	addKeyword(PlusAssign.class);
	addKeyword(Assign.class);
	addKeyword(At.class);
	addKeyword(BitOr.class);

	addKeyword(PlusPlus.class);
	addKeyword(MinusMinus.class);

	addKeyword(Plus.class);
	addKeyword(Minus.class);
	addKeyword(Slash.class);
	addKeyword(Star.class);
	addKeyword(Percent.class);

	addKeyword(LogicalAnd.class);
	addKeyword(BitAnd.class);
	addKeyword(LogicalOr.class);
	addKeyword(BitOr.class);
	addKeyword(Not.class);

	addKeyword(QuestionMark.class);

	addKeyword(Semicolon.class);
	addKeyword(Comma.class);
	addKeyword(Colon.class);
	addKeyword(Dot.class);
    }
}
