package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.interfaces.Annotations;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassModifier extends AbstractJavaParser {

	private static final long serialVersionUID = -5845018908537488666L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Annotations.class) != null) {

		} else {
			expectOneTokenOf(com.puresol.coding.lang.java.source.tokengroups.ClassModifiers.DEFINITIONS);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
