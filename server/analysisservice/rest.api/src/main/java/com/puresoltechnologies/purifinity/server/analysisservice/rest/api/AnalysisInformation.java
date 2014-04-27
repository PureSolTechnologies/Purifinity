package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.Date;

/**
 * This class is for keeping a list of analyzed files within ProjectAnalyzer.
 * This class provides easy and standardized access to the workspace
 * directories.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalysisInformation {

	private final String hashId;
	private final Date time;
	private final long duration;
	private final boolean successful;
	private final String languageName;
	private final String languageVersion;
	private final String analyzerErrorMessage;

	public AnalysisInformation(String hashId, Date time, long duration,
			boolean successful, String languageName, String languageVersion,
			String analyzerErrorMessage) {
		super();
		this.hashId = hashId;
		this.time = time;
		this.duration = duration;
		this.successful = successful;
		this.languageName = languageName;
		this.languageVersion = languageVersion;
		this.analyzerErrorMessage = analyzerErrorMessage;
	}

	public final String getHashId() {
		return hashId;
	}

	public final Date getStartTime() {
		return time;
	}

	public final long getDuration() {
		return duration;
	}

	public final boolean isSuccessful() {
		return successful;
	}

	public final String getLanguageName() {
		return languageName;
	}

	public final String getLanguageVersion() {
		return languageVersion;
	}

	public String getMessage() {
		return analyzerErrorMessage;
	}

}
