package com.puresol.coding.lang.java;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AbstractSourceCodeParser;

/**
 * Standard abstract class for Java parsers for Java grammar.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
abstract public class AbstractJavaParser extends AbstractSourceCodeParser {

    private static final long serialVersionUID = 5924444641661017582L;

    /**
     * {@inheritDoc}
     */
    @Override
    public ProgrammingLanguage getLanguage() {
	return Java.getInstance();
    }
}
