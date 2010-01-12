package com.puresol.coding.java.tokengroups;

import com.puresol.coding.java.tokens.Assign;
import com.puresol.coding.java.tokens.At;
import com.puresol.coding.java.tokens.BitAnd;
import com.puresol.coding.java.tokens.Colon;
import com.puresol.coding.java.tokens.Comma;
import com.puresol.coding.java.tokens.Dot;
import com.puresol.coding.java.tokens.Equal;
import com.puresol.coding.java.tokens.GreaterEqual;
import com.puresol.coding.java.tokens.GreaterThan;
import com.puresol.coding.java.tokens.LCurlyBracket;
import com.puresol.coding.java.tokens.LParen;
import com.puresol.coding.java.tokens.LRectBracket;
import com.puresol.coding.java.tokens.LessEqual;
import com.puresol.coding.java.tokens.LessThan;
import com.puresol.coding.java.tokens.LineBreak;
import com.puresol.coding.java.tokens.LineComment;
import com.puresol.coding.java.tokens.LogicalAnd;
import com.puresol.coding.java.tokens.LogicalOr;
import com.puresol.coding.java.tokens.Minus;
import com.puresol.coding.java.tokens.MinusMinus;
import com.puresol.coding.java.tokens.MultiLineComment;
import com.puresol.coding.java.tokens.Not;
import com.puresol.coding.java.tokens.Percent;
import com.puresol.coding.java.tokens.Plus;
import com.puresol.coding.java.tokens.PlusAssign;
import com.puresol.coding.java.tokens.PlusPlus;
import com.puresol.coding.java.tokens.QuestionMark;
import com.puresol.coding.java.tokens.RCurlyBracket;
import com.puresol.coding.java.tokens.RParen;
import com.puresol.coding.java.tokens.RRectBracket;
import com.puresol.coding.java.tokens.Semicolon;
import com.puresol.coding.java.tokens.Slash;
import com.puresol.coding.java.tokens.Star;
import com.puresol.coding.java.tokens.Unequal;
import com.puresol.coding.java.tokens.BitOr;
import com.puresol.coding.java.tokens.WhiteSpace;
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
