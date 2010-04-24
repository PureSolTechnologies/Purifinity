package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ThrowsKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ThrowsDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -7457344683511563110L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(ThrowsKeyword.class)) {
			expectToken(IdLiteral.class);
			while (acceptToken(Comma.class)) {
				expectToken(IdLiteral.class);
			}
		}
		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
