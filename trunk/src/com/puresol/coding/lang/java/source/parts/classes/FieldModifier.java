package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.interfaces.Annotation;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class FieldModifier extends AbstractJavaParser {

	private static final long serialVersionUID = 4903744780392938101L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Annotation.class) != null) {
		} else {
			expectOneTokenOf(com.puresol.coding.lang.java.source.tokengroups.FieldModifiers.DEFINITIONS);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
