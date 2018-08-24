package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.TimeAwareness;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.versioning.Version;

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
    private final Instant startTime;
    private final Duration duration;
    private final boolean successful;
    private final String languageName;
    private final String languageVersion;
    private final String analyzerId;
    private final Version analyzerVersion;
    private final String analyzerErrorMessage;

    public AnalysisInformation(HashId hashId, Instant startTime, Duration duration, boolean successful,
	    String languageName, String languageVersion, String analyzerId, Version pluginVersion) {
	this(hashId, startTime, duration, successful, languageName, languageVersion, analyzerId, pluginVersion, null);
    }

    public AnalysisInformation(@JsonProperty("hashId") HashId hashId, @JsonProperty("startTime") Instant startTime,
	    @JsonProperty("duration") Duration duration, @JsonProperty("successful") boolean successful,
	    @JsonProperty("languageName") String languageName, @JsonProperty("languageVersion") String languageVersion,
	    @JsonProperty("analyzerId") String analyzerId, @JsonProperty("analyzerVersion") Version analyzerVersion,
	    @JsonProperty("analyzerErrorMessage") String analyzerErrorMessage) {
	super();
	this.hashId = hashId;
	this.startTime = startTime;
	this.duration = duration;
	this.successful = successful;
	this.languageName = languageName;
	this.languageVersion = languageVersion;
	this.analyzerId = analyzerId;
	this.analyzerVersion = analyzerVersion;
	this.analyzerErrorMessage = analyzerErrorMessage;
    }

    public final HashId getHashId() {
	return hashId;
    }

    @Override
    public final Instant getStartTime() {
	return startTime;
    }

    @Override
    public final Duration getDuration() {
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

    public final String getAnalyzerId() {
	return analyzerId;
    }

    public final Version getAnalyzerVersion() {
	return analyzerVersion;
    }

    public boolean wasAnalyzed() {
	return languageName != null;
    }

    public boolean wasError() {
	return (analyzerErrorMessage != null) && (!analyzerErrorMessage.isEmpty());
    }

    public String getAnalyzerErrorMessage() {
	return analyzerErrorMessage;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((analyzerErrorMessage == null) ? 0 : analyzerErrorMessage.hashCode());
	result = prime * result + ((analyzerId == null) ? 0 : analyzerId.hashCode());
	result = prime * result + ((analyzerVersion == null) ? 0 : analyzerVersion.hashCode());
	result = prime * result + ((duration == null) ? 0 : duration.hashCode());
	result = prime * result + ((hashId == null) ? 0 : hashId.hashCode());
	result = prime * result + ((languageName == null) ? 0 : languageName.hashCode());
	result = prime * result + ((languageVersion == null) ? 0 : languageVersion.hashCode());
	result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
	result = prime * result + (successful ? 1231 : 1237);
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
	if (analyzerId == null) {
	    if (other.analyzerId != null)
		return false;
	} else if (!analyzerId.equals(other.analyzerId))
	    return false;
	if (analyzerVersion == null) {
	    if (other.analyzerVersion != null)
		return false;
	} else if (!analyzerVersion.equals(other.analyzerVersion))
	    return false;
	if (duration == null) {
	    if (other.duration != null)
		return false;
	} else if (!duration.equals(other.duration))
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
	if (startTime == null) {
	    if (other.startTime != null)
		return false;
	} else if (!startTime.equals(other.startTime))
	    return false;
	if (successful != other.successful)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	String string = hashId + ":" + languageName + " " + languageVersion + " " + startTime + "/" + duration + "ms";
	if (analyzerErrorMessage != null) {
	    string += " (message:'" + analyzerErrorMessage + "')";
	}
	return string;
    }
}
