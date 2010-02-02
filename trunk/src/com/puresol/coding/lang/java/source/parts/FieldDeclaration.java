package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.symbols.Assign;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class FieldDeclaration extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	processPartIfPossible(FieldModifiers.class);
	processPart(VariableType.class);
	processPart(VariableName.class);
	if (isToken(Assign.class) || isToken(Comma.class)) {
	    skipTokensUntil(Semicolon.class);
	}
	processToken(Semicolon.class);
    }
}
