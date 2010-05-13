package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ClassOrInterfaceType;
import com.puresol.coding.lang.java.source.keywords.ExtendsKeyword;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ExtendsInterfaces extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ExtendsKeyword.class);
		acceptPart(ClassOrInterfaceType.class);
		while (acceptToken(Comma.class) != null) {
			acceptPart(ClassOrInterfaceType.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
