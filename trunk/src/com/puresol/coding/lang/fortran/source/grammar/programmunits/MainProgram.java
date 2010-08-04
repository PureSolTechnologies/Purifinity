package com.puresol.coding.lang.fortran.source.grammar.programmunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.ExecutionPart;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.InternalSubprogramPart;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.SpecificationPart;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1101 main-program is [ program-stmt ]
 * [ specification-part ]
 * [ execution-part ]
 * [ internal-subprogram-part ]
 * end-program-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MainProgram extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(ProgramStmt.class);
		acceptPart(SpecificationPart.class);
		acceptPart(ExecutionPart.class);
		acceptPart(InternalSubprogramPart.class);
		expectPart(EndProgramStmt.class);
		finish();
	}

}
