package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ModuleKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ProcedureKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1206 procedure-stmt is [ MODULE ] PROCEDURE [ :: ] procedure-name-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProcedureStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptToken(ModuleKeyword.class);
		expectToken(ProcedureKeyword.class);
		if (acceptToken(Colon.class) != null) {
			expectToken(Colon.class);
		}
		expectPart(ProcedureNameList.class);
		finish();
	}

}
