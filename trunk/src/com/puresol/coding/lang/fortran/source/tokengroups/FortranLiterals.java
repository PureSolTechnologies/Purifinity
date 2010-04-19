package com.puresol.coding.lang.fortran.source.tokengroups;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.lang.fortran.source.literals.FloatingPointDoublePrecessionLiteral;
import com.puresol.coding.lang.fortran.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.fortran.source.literals.FormatLiteral;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.fortran.source.literals.IntegerLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringStartLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringStopLiteral;
import com.puresol.parser.TokenDefinition;

public class FortranLiterals {

    public static final List<Class<? extends TokenDefinition>> DEFINITIONS = new ArrayList<Class<? extends TokenDefinition>>();

    static {
	DEFINITIONS.add(StringLiteral.class);
	DEFINITIONS.add(StringStartLiteral.class);
	DEFINITIONS.add(StringStopLiteral.class);
	DEFINITIONS.add(FloatingPointDoublePrecessionLiteral.class);
	DEFINITIONS.add(FloatingPointLiteral.class);
	DEFINITIONS.add(IntegerLiteral.class);

	DEFINITIONS.add(IdLiteral.class); // is subset of FormatLiteral!
	DEFINITIONS.add(FormatLiteral.class);
    }
}
