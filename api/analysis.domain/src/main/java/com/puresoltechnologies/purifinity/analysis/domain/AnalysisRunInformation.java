package com.puresoltechnologies.purifinity.analysis.domain;

import static com.puresoltechnologies.commons.misc.ParameterChecks.checkNotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.puresoltechnologies.commons.misc.FileSearchConfiguration;
import com.puresoltechnologies.commons.misc.TimeAwareness;

/**
 * This interface represents a single analysis run for a project.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalysisRunInformation implements Serializable,
		TimeAwareness, Comparable<AnalysisRunInformation> {

	private static final long serialVersionUID = -2618256066434770094L;

	private final UUID projectUUID;
	private final UUID runUUID;
	private final Date startTime;
	private final long duration;
	private final String description;
	private final FileSearchConfiguration fileSearchConfiguration;

	public AnalysisRunInformation(UUID projectUUID, UUID runUUID,
			Date startTime, long duration, String description,
			FileSearchConfiguration fileSearchConfiguration) {
		super();
		checkNotNull("projectUUID", projectUUID);
		checkNotNull("runUUID", runUUID);
		checkNotNull("startTime", startTime);
		checkNotNull("fileSearchConfiguration", fileSearchConfiguration);
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
	public final UUID getUUID() {
		return runUUID;
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
				+ ((projectUUID == null) ? 0 : projectUUID.hashCode());
		result = prime * result + ((runUUID == null) ? 0 : runUUID.hashCode());
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
		if (projectUUID == null) {
			if (other.projectUUID != null)
				return false;
		} else if (!projectUUID.equals(other.projectUUID))
			return false;
		if (runUUID == null) {
			if (other.runUUID != null)
				return false;
		} else if (!runUUID.equals(other.runUUID))
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
		return runUUID.toString() + ": " + startTime + "/" + duration + "ms ("
				+ description + ") search:" + searchString;
	}

}
