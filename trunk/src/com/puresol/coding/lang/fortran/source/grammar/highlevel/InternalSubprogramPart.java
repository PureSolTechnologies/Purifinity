package com.puresol.coding.lang.fortran.source.grammar.highlevel;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.procedures.ContainsStmt;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R210 internal-subprogram-part is contains-stmt
 * [ internal-subprogram ] ...
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InternalSubprogramPart extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ContainsStmt.class);
		while (acceptPart(InternalSubprogram.class) != null) {
		}
		finish();
	}

}
