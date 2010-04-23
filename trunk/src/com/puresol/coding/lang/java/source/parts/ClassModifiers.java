package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassModifiers extends AbstractJavaParser {

    private static final long serialVersionUID = -5845018908537488666L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	while (isCurrentOneOf(com.puresol.coding.lang.java.source.tokengroups.ClassModifiers.DEFINITIONS)) {
	    expectOneOf(com.puresol.coding.lang.java.source.tokengroups.ClassModifiers.DEFINITIONS);
	}
    }

}
