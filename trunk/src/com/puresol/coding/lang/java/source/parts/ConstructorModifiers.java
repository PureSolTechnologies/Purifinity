package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.parser.PartDoesNotMatchException;

public class ConstructorModifiers extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException {
	while (isCurrentOneOf(com.puresol.coding.lang.java.source.tokengroups.ConstructorModifiers.class)) {
	    processOneOf(com.puresol.coding.lang.java.source.tokengroups.ConstructorModifiers.class);
	}
    }

}
