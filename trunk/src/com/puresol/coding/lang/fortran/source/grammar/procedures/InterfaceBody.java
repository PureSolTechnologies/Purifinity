package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.SpecificationPart;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1205 interface-body is function-stmt
 * [ specification-part ]
 * end-function-stmt
 * or subroutine-stmt
 * [ specification-part ]
 * end-subroutine-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InterfaceBody extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(FunctionStmt.class) != null) {
			acceptPart(SpecificationPart.class);
			expectPart(EndFunctionStmt.class);
		} else if (acceptPart(SubroutineStmt.class) != null) {
			acceptPart(SpecificationPart.class);
			expectPart(EndSubroutineStmt.class);
		} else {
			abort();
		}
		finish();
	}

}
