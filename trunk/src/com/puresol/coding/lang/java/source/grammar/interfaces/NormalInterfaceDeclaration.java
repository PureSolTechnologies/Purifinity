package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.TypeParameters;
import com.puresol.coding.lang.java.source.keywords.InterfaceKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class NormalInterfaceDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(InterfaceModifiers.class);
		expectToken(InterfaceKeyword.class);
		String name = getCurrentToken().getText();
		expectToken(Identifier.class);
		acceptPart(TypeParameters.class);
		acceptPart(ExtendsInterfaces.class);
		expectPart(InterfaceBody.class);

		finish(name);
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.INTERFACE;
	}
}
