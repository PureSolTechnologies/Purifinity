package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class EnumBodyDeclarations extends AbstractJavaParser {

	private static final long serialVersionUID = -5500980743550485400L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Semicolon.class);
		acceptPart(ClassBodyDeclarations.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
