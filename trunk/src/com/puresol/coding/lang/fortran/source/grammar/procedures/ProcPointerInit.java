package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.NullInit;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1216 proc-pointer-init is null-init
 * or initial-proc-target
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProcPointerInit extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(NullInit.class) != null) {
		} else (acceptPart(InitialProcTarget.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
