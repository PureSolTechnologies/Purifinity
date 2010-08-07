package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.ExecutionPart;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.InternalSubprogramPart;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.SpecificationPart;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1227 function-subprogram is function-stmt
 * [ specification-part ]
 * [ execution-part ]
 * [ internal-subprogram-part ]
 * end-function-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FunctionSubprogram extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(FunctionStmt.class);
		acceptPart(SpecificationPart.class);
		acceptPart(ExecutionPart.class);
		acceptPart(InternalSubprogramPart.class);
		expectPart(EndFunctionStmt.class);
		finish();
	}

}
