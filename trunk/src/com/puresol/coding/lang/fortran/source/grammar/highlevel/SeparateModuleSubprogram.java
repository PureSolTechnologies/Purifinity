package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1237 separate-module-subprogram is mp-subprogram-stmt
 * [ specification-part ]
 * [ execution-part ]
 * [ internal-subprogram-part ]
 * end-mp-subprogram-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SeparateModuleSubprogram extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(MpSubprogramStmt.class);
		acceptPart(SpecificationPart.class);
		acceptPart(ExecutionPart.class);
		acceptPart(InternalSubprogramPart.class);
		expectPart(EndMpSubprogramStmt.class);
		finish();
	}

}
