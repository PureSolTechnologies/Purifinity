package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.Expr;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1243 stmt-function-stmt is function-name ( [ dummy-arg-name-list ] ) = scalar-expr
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class StmtFunctionStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(FunctionName.class);
		expectToken(LParen.class);
		acceptPart(DummyArgNameList.class);
		expectToken(RParen.class);
		expectToken(Equals.class);
		acceptPart(Expr.class);
		finish();
	}

}
