package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ScalarIntExpr;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R401 type-param-value is scalar-int-expr
 * or *
 * or :
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeParamValue extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ScalarIntExpr.class) != null) {
		} else if (acceptToken(Star.class) != null) {
		} else if (acceptToken(Colon.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
