package com.puresol.coding.lang.java.source.parts.types_values_variables;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ActualTypeArgument extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	public static final List<Class<? extends Parser>> ACTUAL_TYPE_ARGUMENTS = new ArrayList<Class<? extends Parser>>();
	static {
		ACTUAL_TYPE_ARGUMENTS.add(ReferenceType.class);
		ACTUAL_TYPE_ARGUMENTS.add(Wildcard.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectOnePartOf(ACTUAL_TYPE_ARGUMENTS);
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
