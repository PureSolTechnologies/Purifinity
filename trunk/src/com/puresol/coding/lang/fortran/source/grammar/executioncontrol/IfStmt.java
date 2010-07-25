package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.LogicalExpr;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.ActionStmt;
import com.puresol.coding.lang.fortran.source.keywords.IfKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R837 if-stmt is IF ( scalar-logical-expr ) action-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IfStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(IfKeyword.class);
		expectToken(LParen.class);
		expectPart(LogicalExpr.class);
		expectToken(RParen.class);
		acceptPart(ActionStmt.class);
		finish();
	}
}
