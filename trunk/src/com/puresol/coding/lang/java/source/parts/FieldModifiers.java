package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class FieldModifiers extends AbstractJavaParser {

	private static final long serialVersionUID = 4903744780392938101L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (isCurrentOneOf(com.puresol.coding.lang.java.source.tokengroups.FieldModifiers.DEFINITIONS)) {
			expectOneOf(com.puresol.coding.lang.java.source.tokengroups.FieldModifiers.DEFINITIONS);
		}
		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
