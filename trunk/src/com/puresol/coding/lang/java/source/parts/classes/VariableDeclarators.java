package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableDeclarators extends AbstractJavaParser {

	private static final long serialVersionUID = -8995105296970831547L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(VariableDeclarator.class);
		while (acceptToken(Comma.class) != null) {
			expectPart(VariableDeclarator.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
