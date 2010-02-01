package com.puresol.coding.lang.fortran.source.tokengroups;

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
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class FortranSymbols extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {
	addTokenDefinition(LineComment.class);

	addTokenDefinition(LineBreak.class);
	addTokenDefinition(WhiteSpace.class);
	addTokenDefinition(LParen.class);
	addTokenDefinition(RParen.class);

	addTokenDefinition(Equal.class);
	addTokenDefinition(Unequal.class);
	addTokenDefinition(LessThan.class);
	addTokenDefinition(GreaterThan.class);
	addTokenDefinition(LessEqual.class);
	addTokenDefinition(GreaterEqual.class);

	addTokenDefinition(PointerAssign.class);

	addTokenDefinition(Assign.class);
	addTokenDefinition(Slash.class);

	addTokenDefinition(Power.class);
	addTokenDefinition(Star.class);
	addTokenDefinition(Minus.class);
	addTokenDefinition(Plus.class);

	addTokenDefinition(Comma.class);
	addTokenDefinition(Colon.class);
	addTokenDefinition(Semicolon.class);
	addTokenDefinition(Ampersand.class);
	addTokenDefinition(Question.class);
    }
}
