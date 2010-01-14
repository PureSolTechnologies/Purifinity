package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.symbols.Assign;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.PartDoesNotMatchException;

public class FieldDeclaration extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	processPartIfPossible(FieldModifiers.class);
	processPart(VariableType.class);
	processToken(IdLiteral.class);
	if (isToken(Assign.class)) {
	    skipTokensUntil(Semicolon.class);
	}
	processToken(Semicolon.class);
    }

}
