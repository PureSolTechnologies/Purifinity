package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ArrayType extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (!isToken(Identifier.class)) {
			/* This test was included to avoid an endless loop! */
			abort();
		}
		expectPart(Type.class);
		expectToken(LRectBracket.class);
		expectToken(RRectBracket.class);
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
