package com.puresoltechnologies.purifinity.analysis.api;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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

	private final UUID analysisProjectUUID;
	private final UUID uuid;
	private final Date time;
	private final long timeOfRun;
	private final String description;

	public AnalysisRunInformation(UUID analysisProjectUUID, UUID uuid,
			Date time, long timeOfRun, String description) {
		super();
		this.analysisProjectUUID = analysisProjectUUID;
		this.uuid = uuid;
		this.time = time;
		this.timeOfRun = timeOfRun;
		this.description = description;
	}

	/**
	 * This method returns the UUID of the parent analysis for this analysis
	 * run. This UUID is used to load the analysis from the underlying store.
	 * 
	 * @return An UUID object is returned.
	 */
	public final UUID getAnalysisProjectUUID() {
		return analysisProjectUUID;
	}

	/**
	 * This method returns the UUID for this analysis run. This UUID is used to
	 * load the analysis run from the underlying store with the analysis.
	 * 
	 * @return An UUID object is returned.
	 */
	public final UUID getUUID() {
		return uuid;
	}

	/**
	 * This method returns the run start time stamp of the analysis.
	 * 
	 * @return A Date object is returned.
	 */
	@Override
	public final Date getStartTime() {
		return time;
	}

	/**
	 * This method returns the time effort which was needed for analysis.
	 * 
	 * @return Returns the time effort in milliseconds.
	 */
	@Override
	public final long getDuration() {
		return timeOfRun;
	}

	/**
	 * This method returns a String with a simple description for the analysis.
	 * 
	 * @return A String object is returned containing the description.
	 */
	public final String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + (int) (timeOfRun ^ (timeOfRun >>> 32));
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (timeOfRun != other.timeOfRun)
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public int compareTo(AnalysisRunInformation other) {
		return this.time.compareTo(other.time);
	}

}
