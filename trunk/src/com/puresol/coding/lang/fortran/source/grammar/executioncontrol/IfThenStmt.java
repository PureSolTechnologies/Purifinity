package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.LogicalExpr;
import com.puresol.coding.lang.fortran.source.keywords.IfKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ThenKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R833 if-then-stmt is [ if-construct-name : ] IF ( scalar-logical-expr ) THEN
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IfThenStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(IfConstructName.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(IfKeyword.class);
		expectToken(LParen.class);
		expectPart(LogicalExpr.class);
		expectToken(RParen.class);
		expectToken(ThenKeyword.class);
		finish();
	}
}
