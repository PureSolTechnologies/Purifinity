package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.EnumKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class EnumDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -5500980743550485400L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(ClassModifiers.class);
		expectToken(EnumKeyword.class);
		String name = getCurrentToken().getText();
		expectToken(Identifier.class);
		acceptPart(Interfaces.class);
		expectPart(EnumBody.class);
		finish(name);
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.ENUMERATION;
	}
}
