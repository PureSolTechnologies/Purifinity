package com.puresol.coding.analysis;


public class AnalyzerException extends Exception {

    private static final long serialVersionUID = 8839355501039462048L;

    public AnalyzerException(Analyzer analyser) {
	super("Analyser " + analyser.getClass() + " is not suitable for file "
		+ analyser.getFile());
    }

    public AnalyzerException(Exception e) {
	super(e);
    }

}
