package com.puresol.coding.lang.cpp;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AbstractSourceCodeParser;

abstract public class AbstractCPPParser extends AbstractSourceCodeParser {

    /**
     * 
     */
    private static final long serialVersionUID = -7014759421090207124L;

    @Override
    public ProgrammingLanguage getLanguage() {
	return CPlusPlus.getInstance();
    }
}
