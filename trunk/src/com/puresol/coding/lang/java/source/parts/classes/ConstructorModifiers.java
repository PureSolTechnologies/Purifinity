package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ConstructorModifiers extends AbstractJavaParser {

	private static final long serialVersionUID = 8296005430652147879L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (isCurrentTokenOneOf(com.puresol.coding.lang.java.source.tokengroups.ConstructorModifiers.DEFINITIONS)) {
			expectOneTokenOf(com.puresol.coding.lang.java.source.tokengroups.ConstructorModifiers.DEFINITIONS);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
