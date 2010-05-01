package com.puresol.coding.lang.java.source.parts.types_values_variables;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.DoubleKeyword;
import com.puresol.coding.lang.java.source.keywords.FloatKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenDefinition;

public class FloatingPointType extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	private static final List<Class<? extends TokenDefinition>> FLOATING_POINT_TYPES = new ArrayList<Class<? extends TokenDefinition>>();
	static {
		FLOATING_POINT_TYPES.add(FloatKeyword.class);
		FLOATING_POINT_TYPES.add(DoubleKeyword.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectOneTokenOf(FLOATING_POINT_TYPES);
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
