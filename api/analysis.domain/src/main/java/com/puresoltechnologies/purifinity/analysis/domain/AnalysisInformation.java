package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.misc.TimeAwareness;
import com.puresoltechnologies.commons.types.Version;

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
    private final Date startTime;
    private final long duration;
    private final boolean successful;
    private final String languageName;
    private final String languageVersion;
    private final Version pluginVersion;
    private final String analyzerErrorMessage;

    public AnalysisInformation(HashId hashId, Date startTime, long duration,
	    boolean successful, String languageName, String languageVersion,
	    Version pluginVersion) {
	this(hashId, startTime, duration, successful, languageName,
		languageVersion, pluginVersion, null);
    }

    public AnalysisInformation(@JsonProperty("hashId") HashId hashId,
	    @JsonProperty("startTime") Date startTime,
	    @JsonProperty("duration") long duration,
	    @JsonProperty("successful") boolean successful,
	    @JsonProperty("languageName") String languageName,
	    @JsonProperty("languageVersion") String languageVersion,
	    @JsonProperty("pluginVersion") Version pluginVersion,
	    @JsonProperty("analyzerErrorMessage") String analyzerErrorMessage) {
	super();
	this.hashId = hashId;
	this.startTime = startTime;
	this.duration = duration;
	this.successful = successful;
	this.languageName = languageName;
	this.languageVersion = languageVersion;
	this.pluginVersion = pluginVersion;
	this.analyzerErrorMessage = analyzerErrorMessage;
    }

    public final HashId getHashId() {
	return hashId;
    }

    @Override
    public final Date getStartTime() {
	return startTime;
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

    public final Version getPluginVersion() {
	return pluginVersion;
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
	result = prime * result
		+ ((startTime == null) ? 0 : startTime.hashCode());
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
	if (startTime == null) {
	    if (other.startTime != null)
		return false;
	} else if (!startTime.equals(other.startTime))
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

    public String getAnalyzerErrorMessage() {
	return analyzerErrorMessage;
    }

    @Override
    public String toString() {
	String string = hashId + ":" + languageName + " " + languageVersion
		+ " " + startTime + "/" + duration + "ms";
	if (analyzerErrorMessage != null) {
	    string += " (message:'" + analyzerErrorMessage + "')";
	}
	return string;
    }
}
