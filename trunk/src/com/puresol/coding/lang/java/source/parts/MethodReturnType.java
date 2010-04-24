package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodReturnType extends AbstractJavaParser {

	private static final long serialVersionUID = -750943534076760838L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (!acceptToken(VoidKeyword.class)) {
			expectPart(VariableType.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
