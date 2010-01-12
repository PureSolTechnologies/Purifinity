package com.puresol.coding.java.tokengroups;

import com.puresol.coding.java.symbols.Assign;
import com.puresol.coding.java.symbols.At;
import com.puresol.coding.java.symbols.BitAnd;
import com.puresol.coding.java.symbols.BitOr;
import com.puresol.coding.java.symbols.Colon;
import com.puresol.coding.java.symbols.Comma;
import com.puresol.coding.java.symbols.Dot;
import com.puresol.coding.java.symbols.Equal;
import com.puresol.coding.java.symbols.GreaterEqual;
import com.puresol.coding.java.symbols.GreaterThan;
import com.puresol.coding.java.symbols.LCurlyBracket;
import com.puresol.coding.java.symbols.LParen;
import com.puresol.coding.java.symbols.LRectBracket;
import com.puresol.coding.java.symbols.LessEqual;
import com.puresol.coding.java.symbols.LessThan;
import com.puresol.coding.java.symbols.LineBreak;
import com.puresol.coding.java.symbols.LineComment;
import com.puresol.coding.java.symbols.LogicalAnd;
import com.puresol.coding.java.symbols.LogicalOr;
import com.puresol.coding.java.symbols.Minus;
import com.puresol.coding.java.symbols.MinusMinus;
import com.puresol.coding.java.symbols.MultiLineComment;
import com.puresol.coding.java.symbols.Not;
import com.puresol.coding.java.symbols.Percent;
import com.puresol.coding.java.symbols.Plus;
import com.puresol.coding.java.symbols.PlusAssign;
import com.puresol.coding.java.symbols.PlusPlus;
import com.puresol.coding.java.symbols.QuestionMark;
import com.puresol.coding.java.symbols.RCurlyBracket;
import com.puresol.coding.java.symbols.RParen;
import com.puresol.coding.java.symbols.RRectBracket;
import com.puresol.coding.java.symbols.Semicolon;
import com.puresol.coding.java.symbols.Slash;
import com.puresol.coding.java.symbols.Star;
import com.puresol.coding.java.symbols.Unequal;
import com.puresol.coding.java.symbols.WhiteSpace;
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
