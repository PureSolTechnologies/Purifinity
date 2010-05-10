package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class NormalClassDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(ClassModifiers.class);
		expectToken(ClassKeyword.class);
		String name = getCurrentToken().getText();
		expectToken(Identifier.class);
		acceptPart(TypeParameters.class);
		acceptPart(Super.class);
		acceptPart(Interfaces.class);
		expectPart(ClassBody.class);

		finish(name);
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.CLASS;
	}
}
