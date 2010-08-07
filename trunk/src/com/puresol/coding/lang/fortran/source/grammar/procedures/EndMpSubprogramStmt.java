package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndProcedureKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1239 end-mp-subprogram-stmt is END [PROCEDURE [procedure-name]]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EndMpSubprogramStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(EndProcedureKeyword.class) != null) {
			acceptPart(ProcedureName.class);
		} else {
			expectToken(EndKeyword.class);
		}
		finish();
	}

}
