package com.puresol.coding.lang.java.source.grammar.arrays;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ArrayInitializer extends AbstractJavaParser {

	private static final long serialVersionUID = 4903744780392938101L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LCurlyBracket.class);
		acceptPart(VariableInitializers.class);
		acceptToken(Comma.class);
		expectToken(RCurlyBracket.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
