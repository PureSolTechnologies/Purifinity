package com.puresol.purifinity.coding.analysis.api;


/**
 * This exception is thrown in cases of an analyzer issue.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyzerException extends Exception {

    private static final long serialVersionUID = 8839355501039462048L;

    public AnalyzerException(CodeAnalyzer analyser) {
	super("Analyser " + analyser.getClass() + " is not suitable for file "
		+ analyser.getSource());
    }

    public AnalyzerException(Exception e) {
	super(e);
    }

    public AnalyzerException(CodeAnalyzer analyser, Exception e) {
	super("Analyser " + analyser.getClass() + " is not suitable for file "
		+ analyser.getSource(), e);
    }
}
