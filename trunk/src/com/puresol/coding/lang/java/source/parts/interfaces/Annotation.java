package com.puresol.coding.lang.java.source.parts.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Annotation extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	private static final List<Class<? extends Parser>> ANNOTATIONS = new ArrayList<Class<? extends Parser>>();
	static {
		ANNOTATIONS.add(NormalAnnotation.class);
		ANNOTATIONS.add(MarkerAnnotation.class);
		ANNOTATIONS.add(SingleElementAnnotation.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectOnePartOf(ANNOTATIONS);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
