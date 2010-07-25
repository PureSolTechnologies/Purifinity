package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ElseWhereKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R748 elsewhere-stmt is ELSEWHERE [where-construct-name]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ElsewhereStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ElseWhereKeyword.class);
		expectPart(WhereConstructName.class);
		finish();
	}
}
