package com.puresoltechnologies.purifinity.analysis.api;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.puresoltechnologies.commons.misc.TimeAwareness;
import com.puresoltechnologies.commons.misc.io.FileSearchConfiguration;

/**
 * This interface represents a single analysis run for a project.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalysisRunInformation implements Serializable,
	TimeAwareness, Comparable<AnalysisRunInformation> {

    private static final long serialVersionUID = -2618256066434770094L;

    private final String projectId;
    private final long runId;
    private final Date startTime;
    private final long duration;
    private final String description;
    private final FileSearchConfiguration fileSearchConfiguration;

    public AnalysisRunInformation(
	    @JsonProperty("project_id") String projectId,
	    @JsonProperty("run_id") long runId,
	    @JsonProperty("start_time") Date startTime,
	    @JsonProperty("duration") long duration,
	    @JsonProperty("description") String description,
	    @JsonProperty("file_search_configuration") FileSearchConfiguration fileSearchConfiguration) {
	super();
	this.projectId = projectId;
	this.runId = runId;
	this.startTime = startTime;
	this.duration = duration;
	this.description = description;
	this.fileSearchConfiguration = fileSearchConfiguration;
    }

    /**
     * This method returns the UUID of the parent analysis for this analysis
     * run. This UUID is used to load the analysis from the underlying store.
     * 
     * @return An UUID object is returned.
     */
    public final String getProjectId() {
	return projectId;
    }

    /**
     * This method returns the UUID for this analysis run. This UUID is used to
     * load the analysis run from the underlying store with the analysis.
     * 
     * @return An UUID object is returned.
     */
    public final long getRunId() {
	return runId;
    }

    /**
     * This method returns the run start time stamp of the analysis.
     * 
     * @return A Date object is returned.
     */
    @Override
    public final Date getStartTime() {
	return startTime;
    }

    /**
     * This method returns the time effort which was needed for analysis.
     * 
     * @return Returns the time effort in milliseconds.
     */
    @Override
    public final long getDuration() {
	return duration;
    }

    /**
     * This method returns a String with a simple description for the analysis.
     * 
     * @return A String object is returned containing the description.
     */
    public final String getDescription() {
	return description;
    }

    public final FileSearchConfiguration getFileSearchConfiguration() {
	return fileSearchConfiguration;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + (int) (duration ^ (duration >>> 32));
	result = prime
		* result
		+ ((fileSearchConfiguration == null) ? 0
			: fileSearchConfiguration.hashCode());
	result = prime * result
		+ ((projectId == null) ? 0 : projectId.hashCode());
	result = prime * result + (int) (runId ^ (runId >>> 32));
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
	AnalysisRunInformation other = (AnalysisRunInformation) obj;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (duration != other.duration)
	    return false;
	if (fileSearchConfiguration == null) {
	    if (other.fileSearchConfiguration != null)
		return false;
	} else if (!fileSearchConfiguration
		.equals(other.fileSearchConfiguration))
	    return false;
	if (projectId == null) {
	    if (other.projectId != null)
		return false;
	} else if (!projectId.equals(other.projectId))
	    return false;
	if (runId != other.runId)
	    return false;
	if (startTime == null) {
	    if (other.startTime != null)
		return false;
	} else if (!startTime.equals(other.startTime))
	    return false;
	return true;
    }

    @Override
    public int compareTo(AnalysisRunInformation other) {
	return this.startTime.compareTo(other.startTime);
    }

    @Override
    public String toString() {
	String searchString = fileSearchConfiguration != null ? fileSearchConfiguration
		.toString() : "n/a";
	return String.valueOf(runId) + ": " + startTime + "/" + duration
		+ "ms (" + description + ") search:" + searchString;
    }

}
