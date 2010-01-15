package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.PartDoesNotMatchException;

public class ParameterDefinition extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	processToken(LParen.class);
	if (processPartIfPossible(VariableType.class)) {
	    processPart(VariableName.class);
	    while (processTokenIfPossible(Comma.class)) {
		processPart(VariableType.class);
		processPart(VariableName.class);
	    }
	}
	processToken(RParen.class);
    }

}
