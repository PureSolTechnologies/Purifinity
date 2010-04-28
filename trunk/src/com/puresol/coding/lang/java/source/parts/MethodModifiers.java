package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodModifiers extends AbstractJavaParser {

	private static final long serialVersionUID = 1473518113210442270L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (isCurrentOneOf(com.puresol.coding.lang.java.source.tokengroups.MethodModifiers.DEFINITIONS)) {
			expectOneOf(com.puresol.coding.lang.java.source.tokengroups.MethodModifiers.DEFINITIONS);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
