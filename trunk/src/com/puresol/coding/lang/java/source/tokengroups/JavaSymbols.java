package com.puresol.coding.lang.java.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.java.source.symbols.Ampersand;
import com.puresol.coding.lang.java.source.symbols.AmpersandAssign;
import com.puresol.coding.lang.java.source.symbols.Assign;
import com.puresol.coding.lang.java.source.symbols.At;
import com.puresol.coding.lang.java.source.symbols.BackSlash;
import com.puresol.coding.lang.java.source.symbols.BitOr;
import com.puresol.coding.lang.java.source.symbols.BitOrAssign;
import com.puresol.coding.lang.java.source.symbols.Caret;
import com.puresol.coding.lang.java.source.symbols.CaretAssign;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.Equal;
import com.puresol.coding.lang.java.source.symbols.ExclamationMark;
import com.puresol.coding.lang.java.source.symbols.GreaterEqual;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThan;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThanAssign;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThanGreaterThan;
import com.puresol.coding.lang.java.source.symbols.GreaterThanGreaterThanGreaterThanAssign;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.LessEqual;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.coding.lang.java.source.symbols.LessThanLessThan;
import com.puresol.coding.lang.java.source.symbols.LessThanLessThanAssign;
import com.puresol.coding.lang.java.source.symbols.LineBreak;
import com.puresol.coding.lang.java.source.symbols.EndOfLineComment;
import com.puresol.coding.lang.java.source.symbols.LogicalAnd;
import com.puresol.coding.lang.java.source.symbols.LogicalOr;
import com.puresol.coding.lang.java.source.symbols.Minus;
import com.puresol.coding.lang.java.source.symbols.MinusAssign;
import com.puresol.coding.lang.java.source.symbols.MinusMinus;
import com.puresol.coding.lang.java.source.symbols.PercentAssign;
import com.puresol.coding.lang.java.source.symbols.TraditionalComment;
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
import com.puresol.coding.lang.java.source.symbols.SlashAssign;
import com.puresol.coding.lang.java.source.symbols.Star;
import com.puresol.coding.lang.java.source.symbols.StarAssign;
import com.puresol.coding.lang.java.source.symbols.Unequal;
import com.puresol.coding.lang.java.source.symbols.WhiteSpace;
import com.puresol.parser.TokenDefinition;

public class JavaSymbols {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(TraditionalComment.class);
	DEFINITIONS.add(EndOfLineComment.class);
	DEFINITIONS.add(LineBreak.class);
	DEFINITIONS.add(WhiteSpace.class);

	DEFINITIONS.add(LParen.class);
	DEFINITIONS.add(RParen.class);
	DEFINITIONS.add(LCurlyBracket.class);
	DEFINITIONS.add(RCurlyBracket.class);
	DEFINITIONS.add(LRectBracket.class);
	DEFINITIONS.add(RRectBracket.class);

	DEFINITIONS.add(LessEqual.class);
	DEFINITIONS.add(GreaterEqual.class);
	DEFINITIONS.add(LessThanLessThan.class);
	DEFINITIONS.add(LessThan.class);
	DEFINITIONS.add(GreaterThan.class);
	DEFINITIONS.add(GreaterThanGreaterThan.class);
	DEFINITIONS.add(GreaterThanGreaterThanGreaterThan.class);
	DEFINITIONS.add(Equal.class);
	DEFINITIONS.add(Unequal.class);

	DEFINITIONS.add(PlusAssign.class);
	DEFINITIONS.add(MinusAssign.class);
	DEFINITIONS.add(LessThanLessThanAssign.class);
	DEFINITIONS.add(GreaterThanGreaterThanAssign.class);
	DEFINITIONS.add(GreaterThanGreaterThanGreaterThanAssign.class);
	DEFINITIONS.add(AmpersandAssign.class);
	DEFINITIONS.add(CaretAssign.class);
	DEFINITIONS.add(BitOrAssign.class);

	DEFINITIONS.add(SlashAssign.class);
	DEFINITIONS.add(StarAssign.class);
	DEFINITIONS.add(PercentAssign.class);
	DEFINITIONS.add(Assign.class);

	DEFINITIONS.add(At.class);
	DEFINITIONS.add(BitOr.class);
	DEFINITIONS.add(Caret.class);

	DEFINITIONS.add(PlusPlus.class);
	DEFINITIONS.add(MinusMinus.class);

	DEFINITIONS.add(Plus.class);
	DEFINITIONS.add(Minus.class);
	DEFINITIONS.add(Slash.class);
	DEFINITIONS.add(Star.class);
	DEFINITIONS.add(Percent.class);
	DEFINITIONS.add(BackSlash.class);

	DEFINITIONS.add(Ampersand.class);
	DEFINITIONS.add(LogicalAnd.class);
	DEFINITIONS.add(LogicalOr.class);
	DEFINITIONS.add(BitOr.class);
	DEFINITIONS.add(Not.class);

	DEFINITIONS.add(ExclamationMark.class);
	DEFINITIONS.add(QuestionMark.class);

	DEFINITIONS.add(Semicolon.class);
	DEFINITIONS.add(Comma.class);
	DEFINITIONS.add(Colon.class);
	DEFINITIONS.add(Dot.class);
    }
}
