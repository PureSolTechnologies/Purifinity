package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EndWhereKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R749 end-where-stmt is END WHERE [where-construct-name]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EndWhereStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(EndWhereKeyword.class);
		expectPart(WhereConstructName.class);
		finish();
	}
}
