package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.Date;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This interface represents a single analysis run for a project.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalysisRunInformation {

	private final UUID projectUUID;
	private final UUID runUUID;
	private final Date startTime;
	private final long duration;
	private final String description;
	private final FileSearchConfiguration fileSearchConfiguration;

	public AnalysisRunInformation(
			@JsonProperty("projectUUID") UUID projectUUID,
			@JsonProperty("runUUID") UUID runUUID,
			@JsonProperty("startTime") Date startTime,
			@JsonProperty("duration") long duration,
			@JsonProperty("description") String description,
			@JsonProperty("fileSearchConfiguration") FileSearchConfiguration fileSearchConfiguration) {
		super();
		this.projectUUID = projectUUID;
		this.runUUID = runUUID;
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
	public final UUID getProjectUUID() {
		return projectUUID;
	}

	/**
	 * This method returns the UUID for this analysis run. This UUID is used to
	 * load the analysis run from the underlying store with the analysis.
	 * 
	 * @return An UUID object is returned.
	 */
	public final UUID getRunUUID() {
		return runUUID;
	}

	/**
	 * This method returns the run start time stamp of the analysis.
	 * 
	 * @return A Date object is returned.
	 */
	public final Date getStartTime() {
		return startTime;
	}

	/**
	 * This method returns the time effort which was needed for analysis.
	 * 
	 * @return Returns the time effort in milliseconds.
	 */
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

}
