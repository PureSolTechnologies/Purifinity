package com.puresol.coding.lang.fortran.source.tokengroups;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.source.literals.FloatingPointDoublePrecessionLiteral;
import com.puresol.coding.lang.fortran.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.fortran.source.literals.FormatLiteral;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.fortran.source.literals.IntegerLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringStartLiteral;
import com.puresol.coding.lang.fortran.source.literals.StringStopLiteral;
import com.puresol.parser.AbstractTokenDefinitionGroup;
import com.puresol.parser.TokenException;

public class FortranLiterals extends AbstractTokenDefinitionGroup {

	private static final Logger logger = Logger
			.getLogger(FortranLiterals.class);

	@Override
	protected void initialize() {
		try {
			addTokenDefinition(StringLiteral.class);
			addTokenDefinition(StringStartLiteral.class);
			addTokenDefinition(StringStopLiteral.class);
			addTokenDefinition(FloatingPointDoublePrecessionLiteral.class);
			addTokenDefinition(FloatingPointLiteral.class);
			addTokenDefinition(IntegerLiteral.class);

			addTokenDefinition(IdLiteral.class); // is subset of FormatLiteral!
			addTokenDefinition(FormatLiteral.class);
		} catch (TokenException e) {
			logger.error(e.getMessage());
		}
	}
}
