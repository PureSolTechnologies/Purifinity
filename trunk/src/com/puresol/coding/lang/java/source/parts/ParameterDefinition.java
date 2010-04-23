package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ParameterDefinition extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LParen.class);
	if (acceptPart(VariableType.class)) {
	    expectPart(VariableName.class);
	    while (acceptToken(Comma.class)) {
		expectPart(VariableType.class);
		expectPart(VariableName.class);
	    }
	}
	expectToken(RParen.class);
    }

}
