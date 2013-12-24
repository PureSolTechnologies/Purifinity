package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.Serializable;
import java.util.Date;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.TimeAwareness;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;

/**
 * This class is for keeping a list of analyzed files within ProjectAnalyzer.
 * This class provides easy and standardized access to the workspace
 * directories.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalyzedCode implements Comparable<AnalyzedCode>,
		Serializable, TimeAwareness {

	private static final long serialVersionUID = 2030120585873480183L;

	private final HashId hashId;
	private final SourceCodeLocation source;
	private final Date time;
	private final long timeOfRun;
	private final String languageName;
	private final String languageVersion;
	private final String analyzerErrorMessage;

	public AnalyzedCode(HashId hashId, SourceCodeLocation source, Date time,
			long timeOfRun, String languageName, String languageVersion) {
		this(hashId, source, time, timeOfRun, languageName, languageVersion,
				null);
	}

	public AnalyzedCode(HashId hashId, SourceCodeLocation source, Date time,
			long timeOfRun, String languageName, String languageVersion,
			String analyzerErrorMessage) {
		super();
		this.hashId = hashId;
		this.source = source;
		this.time = time;
		this.timeOfRun = timeOfRun;
		this.languageName = languageName;
		this.languageVersion = languageVersion;
		this.analyzerErrorMessage = analyzerErrorMessage;
	}

	public final HashId getHashId() {
		return hashId;
	}

	public final SourceCodeLocation getSourceLocation() {
		return source;
	}

	@Override
	public final Date getStartTime() {
		return time;
	}

	@Override
	public final long getDuration() {
		return timeOfRun;
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
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
		result = prime * result
				+ ((languageName == null) ? 0 : languageName.hashCode());
		result = prime * result
				+ ((languageVersion == null) ? 0 : languageVersion.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + (int) (timeOfRun ^ (timeOfRun >>> 32));
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
		AnalyzedCode other = (AnalyzedCode) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
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
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (timeOfRun != other.timeOfRun)
			return false;
		return true;
	}

	@Override
	public int compareTo(AnalyzedCode other) {
		return this.source.getHumanReadableLocationString().compareTo(
				other.source.getHumanReadableLocationString());
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
}
