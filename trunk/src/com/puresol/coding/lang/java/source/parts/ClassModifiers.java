package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassModifiers extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	while (isCurrentOneOf(com.puresol.coding.lang.java.source.tokengroups.ClassModifiers.DEFINITIONS)) {
	    processOneOf(com.puresol.coding.lang.java.source.tokengroups.ClassModifiers.DEFINITIONS);
	}
    }

}
