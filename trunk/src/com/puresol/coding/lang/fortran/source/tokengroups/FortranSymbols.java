package com.puresol.coding.lang.fortran.source.tokengroups;

import com.puresol.coding.lang.fortran.source.symbols.Assign;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.DotEQDot;
import com.puresol.coding.lang.fortran.source.symbols.DotGTDot;
import com.puresol.coding.lang.fortran.source.symbols.DotLTDot;
import com.puresol.coding.lang.fortran.source.symbols.DotNEDot;
import com.puresol.coding.lang.fortran.source.symbols.DotORDot;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LineBreak;
import com.puresol.coding.lang.fortran.source.symbols.Minus;
import com.puresol.coding.lang.fortran.source.symbols.Plus;
import com.puresol.coding.lang.fortran.source.symbols.Power;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.coding.lang.fortran.source.symbols.WhiteSpace;
import com.puresol.parser.AbstractTokenDefinitionGroup;

public class FortranSymbols extends AbstractTokenDefinitionGroup {

    @Override
    protected void initialize() {
	addTokenDefinition(LineBreak.class);
	addTokenDefinition(WhiteSpace.class);
	addTokenDefinition(LParen.class);
	addTokenDefinition(RParen.class);

	addTokenDefinition(DotEQDot.class);
	addTokenDefinition(DotNEDot.class);
	addTokenDefinition(DotLTDot.class);
	addTokenDefinition(DotGTDot.class);

	addTokenDefinition(DotORDot.class);

	addTokenDefinition(Assign.class);

	addTokenDefinition(Power.class);
	addTokenDefinition(Star.class);
	addTokenDefinition(Minus.class);
	addTokenDefinition(Plus.class);

	addTokenDefinition(Comma.class);
    }
}
