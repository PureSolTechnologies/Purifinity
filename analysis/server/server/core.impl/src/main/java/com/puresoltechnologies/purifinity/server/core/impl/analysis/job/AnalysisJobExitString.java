package com.puresoltechnologies.purifinity.server.core.impl.analysis.job;

/**
 * This enum contains the possible exit strings for the AnalysisJob.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public enum AnalysisJobExitString {

    ALREADY_QUEUED("ALREADY QUEUED"), //
    SUCCESSFUL("SUCCESSFUL"), //
    SKIPPED("SKIPPED"), //
    ABORT("ABORT"), //
    ;

    private final String exitString;

    private AnalysisJobExitString(String exitString) {
	this.exitString = exitString;
    }

    public String get() {
	return exitString;
    }
}
