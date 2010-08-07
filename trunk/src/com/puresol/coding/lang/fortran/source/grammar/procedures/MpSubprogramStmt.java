package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ModuleProcedureKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1238 mp-subprogram-stmt is MODULE PROCEDURE procedure-name
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MpSubprogramStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ModuleProcedureKeyword.class);
		expectPart(ProcedureName.class);
		finish();
	}

}
