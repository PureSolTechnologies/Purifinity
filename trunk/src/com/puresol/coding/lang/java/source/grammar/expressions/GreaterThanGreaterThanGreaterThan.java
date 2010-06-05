package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class GreaterThanGreaterThanGreaterThan extends AbstractJavaParser {

	private static final long serialVersionUID = -6515420221655472408L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(GreaterThan.class);
		expectToken(GreaterThan.class);
		expectToken(GreaterThan.class);
		finish();
	}

}
