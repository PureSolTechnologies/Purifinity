package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassModifiers extends AbstractJavaParser {

	private static final long serialVersionUID = -5845018908537488666L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		while (acceptPart(ClassModifier.class) != null) {
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
