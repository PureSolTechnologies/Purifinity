package com.puresol.coding.lang.fortran.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.WhereKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R743 where-construct-stmt is [where-construct-name:] WHERE ( mask-expr )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class WhereConstructStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(WhereConstructName.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(WhereKeyword.class);
		expectToken(LParen.class);
		expectPart(MaskExpr.class);
		expectToken(RParen.class);
		finish();
	}
}
