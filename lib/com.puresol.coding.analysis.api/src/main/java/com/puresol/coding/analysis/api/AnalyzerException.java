package com.puresol.coding.analysis.api;

/**
 * This exception is thrown in cases of an analyzer issue.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnalyzerException extends Exception {

    private static final long serialVersionUID = 8839355501039462048L;

    public AnalyzerException(Analyzer analyser) {
	super("Analyser " + analyser.getClass() + " is not suitable for file "
		+ analyser.getFile());
    }

    public AnalyzerException(Exception e) {
	super(e);
    }

    public AnalyzerException(Analyzer analyser, Exception e) {
	super("Analyser " + analyser.getClass() + " is not suitable for file "
		+ analyser.getFile(), e);
    }
}
