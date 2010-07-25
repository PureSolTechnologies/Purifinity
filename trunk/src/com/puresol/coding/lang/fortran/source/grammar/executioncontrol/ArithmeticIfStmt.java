package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.NumericExpr;
import com.puresol.coding.lang.fortran.source.grammar.lexical.Label;
import com.puresol.coding.lang.fortran.source.keywords.IfKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R853 arithmetic-if-stmt is IF ( scalar-numeric-expr ) label , label , label
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ArithmeticIfStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(IfKeyword.class);
		expectToken(LParen.class);
		expectPart(NumericExpr.class);
		expectToken(RParen.class);
		expectPart(Label.class);
		expectToken(Comma.class);
		expectPart(Label.class);
		expectToken(Comma.class);
		expectPart(Label.class);
		finish();
	}
}
