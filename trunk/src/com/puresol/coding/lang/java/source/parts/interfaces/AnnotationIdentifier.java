package com.puresol.coding.lang.java.source.parts.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class AnnotationIdentifier extends AbstractJavaParser {

	private static final long serialVersionUID = -3504255659193351546L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
