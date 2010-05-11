package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ConstructorModifiers extends AbstractJavaParser {

	private static final long serialVersionUID = 8296005430652147879L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ConstructorModifier.class);
		while (acceptPart(ConstructorModifier.class) != null) {
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
