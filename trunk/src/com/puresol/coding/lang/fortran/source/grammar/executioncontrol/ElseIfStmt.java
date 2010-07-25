package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.LogicalExpr;
import com.puresol.coding.lang.fortran.source.keywords.ElseIfKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ThenKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R834 else-if-stmt is ELSE IF ( scalar-logical-expr ) THEN [ if-construct-name ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ElseIfStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ElseIfKeyword.class);
		expectToken(LParen.class);
		expectPart(LogicalExpr.class);
		expectToken(RParen.class);
		expectToken(ThenKeyword.class);
		acceptPart(IfConstructName.class);
		finish();
	}
}
