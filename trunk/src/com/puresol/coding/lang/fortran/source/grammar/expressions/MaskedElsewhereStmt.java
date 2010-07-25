package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ElseWhereKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R747 masked-elsewhere-stmt is ELSEWHERE (mask-expr) [where-construct-name]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MaskedElsewhereStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ElseWhereKeyword.class);
		expectToken(LParen.class);
		expectPart(MaskExpr.class);
		expectToken(RParen.class);
		expectPart(WhereConstructName.class);
		finish();
	}
}
