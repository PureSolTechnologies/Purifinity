package com.puresol.coding.java;

import com.puresol.coding.java.tokens.Assign;
import com.puresol.coding.java.tokens.BooleanLiteral;
import com.puresol.coding.java.tokens.Equal;
import com.puresol.coding.java.tokens.FloatingPointLiteral;
import com.puresol.coding.java.tokens.GreaterEqual;
import com.puresol.coding.java.tokens.IdLiteral;
import com.puresol.coding.java.tokens.IntegerLiteral;
import com.puresol.coding.java.tokens.LessEqual;
import com.puresol.coding.java.tokens.MinusMinus;
import com.puresol.coding.java.tokens.PlusAssign;
import com.puresol.coding.java.tokens.PlusPlus;
import com.puresol.coding.java.tokens.StringLiteral;
import com.puresol.coding.java.tokens.Unequal;
import com.puresol.parser.AbstractTokenDefinitions;
import com.puresol.parser.defaulttokens.At;
import com.puresol.parser.defaulttokens.Colon;
import com.puresol.parser.defaulttokens.Dot;
import com.puresol.parser.defaulttokens.GreaterThan;
import com.puresol.parser.defaulttokens.LCurlyBracket;
import com.puresol.parser.defaulttokens.LParen;
import com.puresol.parser.defaulttokens.LRectBracket;
import com.puresol.parser.defaulttokens.LessThan;
import com.puresol.parser.defaulttokens.LineBreak;
import com.puresol.parser.defaulttokens.Minus;
import com.puresol.parser.defaulttokens.Percent;
import com.puresol.parser.defaulttokens.Plus;
import com.puresol.parser.defaulttokens.RCurlyBracket;
import com.puresol.parser.defaulttokens.RParen;
import com.puresol.parser.defaulttokens.RRectBracket;
import com.puresol.parser.defaulttokens.Semicolon;
import com.puresol.parser.defaulttokens.Slash;
import com.puresol.parser.defaulttokens.Star;
import com.puresol.parser.defaulttokens.WhiteSpace;

public class JavaSymbols extends AbstractTokenDefinitions {

	@Override
	protected void initialize() {
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

		addKeyword(PlusPlus.class);
		addKeyword(MinusMinus.class);

		addKeyword(Plus.class);
		addKeyword(Minus.class);
		addKeyword(Slash.class);
		addKeyword(Star.class);
		addKeyword(Percent.class);

		addKeyword(Semicolon.class);
		addKeyword(Colon.class);
		addKeyword(Dot.class);

		addKeyword(FloatingPointLiteral.class);
		addKeyword(IntegerLiteral.class);
		addKeyword(StringLiteral.class);
		addKeyword(BooleanLiteral.class);

		addKeyword(IdLiteral.class);
	}
}
