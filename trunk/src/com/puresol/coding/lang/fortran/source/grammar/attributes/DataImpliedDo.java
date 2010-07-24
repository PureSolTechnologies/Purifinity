package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ContiguousKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DataKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.Slash;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R537 data-implied-do is ( data-i-do-object-list , data-i-do-variable = scalar-int-constant-expr , scalar-int-constant-expr [ , scalar-int-constant-expr ] )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DataImpliedDo extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LParen.class);
		expectPart(DataIDoObjectList.class);
		expectToken(Comma.class);
		expectPart(DataIDoVariable.class);
		expectToken(Equals.class);
		expectPart(ScalarIntConstantExpr)
		expectToken(Comma.class);
		expectPart(ScalarIntConstantExpr)
		while (acceptToken(Comma.class) != null) {
			expectPart(ScalarIntConstantExpr)
		}
		expectToken(RParen.class);
		finish();
	}
}
