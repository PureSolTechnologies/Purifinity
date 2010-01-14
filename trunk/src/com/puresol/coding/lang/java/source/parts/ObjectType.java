package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.PartDoesNotMatchException;

public class ObjectType extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	processToken(IdLiteral.class);
	while (processTokenIfPossible(Dot.class)) {
	    processToken(IdLiteral.class);
	}
	processPartIfPossible(Generic.class);
    }

}
