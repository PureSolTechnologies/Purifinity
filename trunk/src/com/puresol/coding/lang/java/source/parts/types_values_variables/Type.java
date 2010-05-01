package com.puresol.coding.lang.java.source.parts.types_values_variables;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Type extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	private static final List<Class<? extends Parser>> TYPES = new ArrayList<Class<? extends Parser>>();
	static {
		TYPES.add(PrimitiveType.class);
		TYPES.add(ReferenceType.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectOnePartOf(TYPES);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	public String getVariableTypeName() {
		return getContinuousText();
	}
}
