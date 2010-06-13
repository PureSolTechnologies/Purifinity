package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.IntegerKeyword;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R432 type-param-decl is type-param-name [ = scalar-int-constant-expr ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeParamDecl extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(NameLiteral.class);
		if (acceptToken(Equals.class) != null) {
			expectPart(ScalarIntConstantExpr.class);
		}
		finish();
	}

}
