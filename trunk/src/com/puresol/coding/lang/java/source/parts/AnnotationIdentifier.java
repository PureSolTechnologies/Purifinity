package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.At;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class AnnotationIdentifier extends AbstractJavaParser {

	private static final long serialVersionUID = -3504255659193351546L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(At.class);
		expectToken(IdLiteral.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
