package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.symbols.Assign;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.coding.langelements.VariableLanguageElement;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class FieldDeclaration extends AbstractJavaParser implements
		VariableLanguageElement {

	private static final long serialVersionUID = -8995105296970831547L;

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(FieldModifiers.class);
		expectPart(VariableType.class);
		String name = getCurrentToken().getText();
		expectPart(VariableName.class);
		if (isToken(Assign.class) || isToken(Comma.class)) {
			skipTo(Semicolon.class);
		}
		expectToken(Semicolon.class);
		finish(name);
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}
}
