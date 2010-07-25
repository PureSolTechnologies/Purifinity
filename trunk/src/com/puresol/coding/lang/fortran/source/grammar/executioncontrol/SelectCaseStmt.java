package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.SelectCaseKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R839 select-case-stmt is [ case-construct-name : ] SELECT CASE ( case-expr )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SelectCaseStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(CaseConstructName.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(SelectCaseKeyword.class);
		expectToken(LParen.class);
		expectPart(CaseExpr.class);
		expectToken(RParen.class);
		finish();
	}
}
