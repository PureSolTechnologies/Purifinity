package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ByteKeyword;
import com.puresol.coding.lang.java.source.keywords.CharKeyword;
import com.puresol.coding.lang.java.source.keywords.IntKeyword;
import com.puresol.coding.lang.java.source.keywords.LongKeyword;
import com.puresol.coding.lang.java.source.keywords.ShortKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenDefinition;

public class IntegralType extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	private static final List<Class<? extends TokenDefinition>> INTEGRAL_TYPES = new ArrayList<Class<? extends TokenDefinition>>();
	static {
		INTEGRAL_TYPES.add(ByteKeyword.class);
		INTEGRAL_TYPES.add(ShortKeyword.class);
		INTEGRAL_TYPES.add(IntKeyword.class);
		INTEGRAL_TYPES.add(LongKeyword.class);
		INTEGRAL_TYPES.add(CharKeyword.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectOneTokenOf(INTEGRAL_TYPES);
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
