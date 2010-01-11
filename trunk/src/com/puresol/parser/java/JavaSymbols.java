package com.puresol.parser.java;

import com.puresol.parser.AbstractTokenDefinitions;
import com.puresol.parser.defaulttokendef.At;
import com.puresol.parser.defaulttokendef.Colon;
import com.puresol.parser.defaulttokendef.Dot;
import com.puresol.parser.defaulttokendef.GreaterThan;
import com.puresol.parser.defaulttokendef.LCurlyBracket;
import com.puresol.parser.defaulttokendef.LParen;
import com.puresol.parser.defaulttokendef.LRectBracket;
import com.puresol.parser.defaulttokendef.LessThan;
import com.puresol.parser.defaulttokendef.LineBreak;
import com.puresol.parser.defaulttokendef.Minus;
import com.puresol.parser.defaulttokendef.Percent;
import com.puresol.parser.defaulttokendef.Plus;
import com.puresol.parser.defaulttokendef.RCurlyBracket;
import com.puresol.parser.defaulttokendef.RParen;
import com.puresol.parser.defaulttokendef.RRectBracket;
import com.puresol.parser.defaulttokendef.Semicolon;
import com.puresol.parser.defaulttokendef.Slash;
import com.puresol.parser.defaulttokendef.Star;
import com.puresol.parser.defaulttokendef.WhiteSpace;
import com.puresol.parser.java.tokendef.Assign;
import com.puresol.parser.java.tokendef.BooleanLiteral;
import com.puresol.parser.java.tokendef.Equal;
import com.puresol.parser.java.tokendef.FloatingPointLiteral;
import com.puresol.parser.java.tokendef.GreaterEqual;
import com.puresol.parser.java.tokendef.ID;
import com.puresol.parser.java.tokendef.IntegerLiteral;
import com.puresol.parser.java.tokendef.LessEqual;
import com.puresol.parser.java.tokendef.MinusMinus;
import com.puresol.parser.java.tokendef.PlusAssign;
import com.puresol.parser.java.tokendef.PlusPlus;
import com.puresol.parser.java.tokendef.StringLiteral;
import com.puresol.parser.java.tokendef.Unequal;

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

	addKeyword(ID.class);
    }
}
