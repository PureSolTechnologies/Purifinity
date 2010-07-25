package com.puresol.coding.lang.fortran.source.grammar.lexical;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.BozLiteral;
import com.puresol.coding.lang.fortran.source.literals.CharLiteral;
import com.puresol.coding.lang.fortran.source.literals.ComplexLiteral;
import com.puresol.coding.lang.fortran.source.literals.IntegerLiteral;
import com.puresol.coding.lang.fortran.source.literals.LogicalLiteral;
import com.puresol.coding.lang.fortran.source.literals.RealLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R305 literal-constant is int-literal-constant
 * or real-literal-constant
 * or complex-literal-constant
 * or logical-literal-constant
 * or char-literal-constant
 * or boz-literal-constant
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LiteralConstant extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(IntegerLiteral.class) != null) {
		} else if (acceptToken(RealLiteral.class) != null) {
		} else if (acceptToken(ComplexLiteral.class) != null) {
		} else if (acceptToken(LogicalLiteral.class) != null) {
		} else if (acceptToken(CharLiteral.class) != null) {
		} else if (acceptToken(BozLiteral.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
