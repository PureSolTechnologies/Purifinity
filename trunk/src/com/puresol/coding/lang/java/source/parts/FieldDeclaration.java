package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.symbols.Assign;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class FieldDeclaration extends AbstractSourceCodeParser {

	@SuppressWarnings("unchecked")
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(FieldModifiers.class);
		expectPart(VariableType.class);
		expectPart(VariableName.class);
		if (isToken(Assign.class) || isToken(Comma.class)) {
			skipTo(Semicolon.class);
		}
		expectToken(Semicolon.class);
	}
}
