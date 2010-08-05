package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1214 proc-decl is procedure-entity-name [ => proc-pointer-init ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProcDecl extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ProcedureEntityName.class);
		if (acceptToken(Equals.class) != null) {
			expectToken(GreaterThan.class);
			expectPart(ProcPointerInit.class);
		}
		finish();
	}
}
