package com.puresoltechnologies.purifinity.analysis.domain;

import static com.puresoltechnologies.commons.misc.ParameterChecks.checkNotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * This is the central interface for information about a single analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalysisProjectInformation implements Serializable {

	private static final long serialVersionUID = 1130436909310501842L;

	private final UUID uuid;
	private final Date creationTime;

	public AnalysisProjectInformation(UUID uuid, Date creationTime) {
		super();
		checkNotNull("uuid", uuid);
		checkNotNull("creationTime", creationTime);
		this.uuid = uuid;
		this.creationTime = creationTime;
	}

	/**
	 * Returns the UUID for the analysis. This UUID is used to load the analysis
	 * from the store.
	 * 
	 * @return An UUID object is returned.
	 */
	public final UUID getUUID() {
		return uuid;
	}

	/**
	 * This method returns the time stamp of the creation of this analysis.
	 * 
	 * @return A {@link Date} object is returned containing the universal time
	 *         stamp of the creation time.
	 */
	public final Date getCreationTime() {
		return creationTime;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalysisProjectInformation other = (AnalysisProjectInformation) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
