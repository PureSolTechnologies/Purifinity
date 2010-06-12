package com.puresol.coding.lang.fortran.source.grammar.types.derivedtypes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R447 type-bound-proc-binding is type-bound-procedure-stmt
 * or type-bound-generic-stmt
 * or final-procedure-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeBoundProcBinding extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(TypeBoundProcedureStmt.class) != null) {
		} else if (acceptPart(TypeBoundGenericStmt.class) != null) {
		} else if (acceptPart(FinalProcedureStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
