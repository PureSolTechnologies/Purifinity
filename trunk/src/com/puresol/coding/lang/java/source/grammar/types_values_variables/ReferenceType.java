package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ReferenceType extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	private static final List<Class<? extends Parser>> REFERENCE_TYPES = new ArrayList<Class<? extends Parser>>();
	static {
		REFERENCE_TYPES.add(ClassOrInterfaceType.class);
		REFERENCE_TYPES.add(TypeVariable.class);
		REFERENCE_TYPES.add(ArrayType.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectOnePartOf(REFERENCE_TYPES);
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
