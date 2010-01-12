package com.puresol.coding.lang.fortran.source.tokengroups;

import com.puresol.coding.lang.fortran.source.symbols.Assign;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
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
		addKeyword(LineBreak.class);
		addKeyword(WhiteSpace.class);
		addKeyword(LParen.class);
		addKeyword(RParen.class);

		addKeyword(Assign.class);

		addKeyword(Power.class);
		addKeyword(Star.class);
		addKeyword(Minus.class);
		addKeyword(Plus.class);

		addKeyword(Comma.class);
	}
}
