package com.puresol.coding.lang.fortran.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.fortran.source.symbols.Ampersand;
import com.puresol.coding.lang.fortran.source.symbols.Assign;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equal;
import com.puresol.coding.lang.fortran.source.symbols.GreaterEqual;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LessEqual;
import com.puresol.coding.lang.fortran.source.symbols.LessThan;
import com.puresol.coding.lang.fortran.source.symbols.LineBreak;
import com.puresol.coding.lang.fortran.source.symbols.LineComment;
import com.puresol.coding.lang.fortran.source.symbols.Minus;
import com.puresol.coding.lang.fortran.source.symbols.Plus;
import com.puresol.coding.lang.fortran.source.symbols.PointerAssign;
import com.puresol.coding.lang.fortran.source.symbols.Power;
import com.puresol.coding.lang.fortran.source.symbols.Question;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.Semicolon;
import com.puresol.coding.lang.fortran.source.symbols.Slash;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.coding.lang.fortran.source.symbols.Unequal;
import com.puresol.coding.lang.fortran.source.symbols.WhiteSpace;
import com.puresol.parser.TokenDefinition;

public class FortranSymbols {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(LineComment.class);

	DEFINITIONS.add(LineBreak.class);
	DEFINITIONS.add(WhiteSpace.class);
	DEFINITIONS.add(LParen.class);
	DEFINITIONS.add(RParen.class);

	DEFINITIONS.add(Equal.class);
	DEFINITIONS.add(Unequal.class);
	DEFINITIONS.add(LessThan.class);
	DEFINITIONS.add(GreaterThan.class);
	DEFINITIONS.add(LessEqual.class);
	DEFINITIONS.add(GreaterEqual.class);

	DEFINITIONS.add(PointerAssign.class);

	DEFINITIONS.add(Assign.class);
	DEFINITIONS.add(Slash.class);

	DEFINITIONS.add(Power.class);
	DEFINITIONS.add(Star.class);
	DEFINITIONS.add(Minus.class);
	DEFINITIONS.add(Plus.class);

	DEFINITIONS.add(Comma.class);
	DEFINITIONS.add(Colon.class);
	DEFINITIONS.add(Semicolon.class);
	DEFINITIONS.add(Ampersand.class);
	DEFINITIONS.add(Question.class);
    }
}
