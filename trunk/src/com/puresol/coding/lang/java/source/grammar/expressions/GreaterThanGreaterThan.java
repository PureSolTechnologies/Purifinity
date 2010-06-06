package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * This part was created to due some ambiguous constructs with shift operator
 * and nested type parameters.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GreaterThanGreaterThan extends AbstractJavaParser {

	private static final long serialVersionUID = -6515420221655472408L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(GreaterThan.class);
		expectToken(GreaterThan.class);
		finish();
	}

}
