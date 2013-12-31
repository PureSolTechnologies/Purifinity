package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.Serializable;
import java.util.Date;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.TimeAwareness;

/**
 * This class is for keeping a list of analyzed files within ProjectAnalyzer.
 * This class provides easy and standardized access to the workspace
 * directories.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalysisInformation implements Serializable, TimeAwareness {

	private static final long serialVersionUID = 2030120585873480183L;

	private final HashId hashId;
	private final Date time;
	private final long duration;
	private final boolean successful;
	private final String languageName;
	private final String languageVersion;
	private final String analyzerErrorMessage;

	public AnalysisInformation(HashId hashId, Date time, long duration,
			boolean successful, String languageName, String languageVersion) {
		this(hashId, time, duration, successful, languageName, languageVersion,
				null);
	}

	public AnalysisInformation(HashId hashId, Date time, long duration,
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

	public final HashId getHashId() {
		return hashId;
	}

	@Override
	public final Date getStartTime() {
		return time;
	}

	@Override
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((analyzerErrorMessage == null) ? 0 : analyzerErrorMessage
						.hashCode());
		result = prime * result + (int) (duration ^ (duration >>> 32));
		result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
		result = prime * result
				+ ((languageName == null) ? 0 : languageName.hashCode());
		result = prime * result
				+ ((languageVersion == null) ? 0 : languageVersion.hashCode());
		result = prime * result + (successful ? 1231 : 1237);
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalysisInformation other = (AnalysisInformation) obj;
		if (analyzerErrorMessage == null) {
			if (other.analyzerErrorMessage != null)
				return false;
		} else if (!analyzerErrorMessage.equals(other.analyzerErrorMessage))
			return false;
		if (duration != other.duration)
			return false;
		if (hashId == null) {
			if (other.hashId != null)
				return false;
		} else if (!hashId.equals(other.hashId))
			return false;
		if (languageName == null) {
			if (other.languageName != null)
				return false;
		} else if (!languageName.equals(other.languageName))
			return false;
		if (languageVersion == null) {
			if (other.languageVersion != null)
				return false;
		} else if (!languageVersion.equals(other.languageVersion))
			return false;
		if (successful != other.successful)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	public boolean wasAnalyzed() {
		return languageName != null;
	}

	public boolean wasError() {
		return (analyzerErrorMessage != null)
				&& (!analyzerErrorMessage.isEmpty());
	}

	public String getMessage() {
		return analyzerErrorMessage;
	}

	@Override
	public String toString() {
		String string = hashId + ":" + languageName + " " + languageVersion
				+ " " + time + "/" + duration + "ms";
		if (analyzerErrorMessage != null) {
			string += " (message:'" + analyzerErrorMessage + "')";
		}
		return string;
	}
}
