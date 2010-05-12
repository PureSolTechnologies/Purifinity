package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class TypeVariable extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Identifier.class);
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
