package com.puresol.coding.lang.fortran.source.grammar.programunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.OperatorKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.GreaterThan;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1111 rename is local-name => use-name
 * or OPERATOR (local-defined-operator) => OPERATOR (use-defined-operator)
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Rename extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(NameLiteral.class) != null) {
			expectToken(Equals.class);
			expectToken(GreaterThan.class);
			expectToken(NameLiteral.class);
		} else if (acceptToken(OperatorKeyword.class) != null) {
			expectToken(LParen.class);
			expectPart(LocalDefinedOperator.class);
			expectToken(RParen.class);
			expectToken(Equals.class);
			expectToken(GreaterThan.class);
			expectToken(OperatorKeyword.class);
			expectToken(LParen.class);
			expectPart(UseDefinedOperator.class);
			expectToken(RParen.class);
		} else {
			abort();
		}
		finish();
	}
}
