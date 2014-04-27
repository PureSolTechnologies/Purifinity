package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.Date;
import java.util.UUID;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This is the central interface for information about a single analysis.
 * 
 * @author Rick-Rainer Ludwig
 */
public final class AnalysisProjectInformation {

	private final UUID uuid;
	private final Date creationTime;

	public AnalysisProjectInformation(@JsonProperty("uuid") UUID uuid,
			@JsonProperty("creationTime") Date creationTime) {
		super();
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
}
