package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1202 interface-specification is interface-body
 * or procedure-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InterfaceSpecification extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(InterfaceBody.class) != null) {
		} else if (acceptPart(ProcedureStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
