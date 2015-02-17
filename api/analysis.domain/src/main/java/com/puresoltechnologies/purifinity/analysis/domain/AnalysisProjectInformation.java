package com.puresoltechnologies.purifinity.analysis.domain;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * This is the central interface for information about a single analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalysisProjectInformation implements Serializable {

    private static final long serialVersionUID = 1130436909310501842L;

    private final String projectId;
    private final Date creationTime;

    public AnalysisProjectInformation(
	    @JsonProperty("project_id") String projectId,
	    @JsonProperty("creationTime") Date creationTime) {
	super();
	this.projectId = projectId;
	this.creationTime = creationTime;
    }

    /**
     * Returns the UUID for the analysis. This UUID is used to load the analysis
     * from the store.
     * 
     * @return An UUID object is returned.
     */
    public final String getProjectId() {
	return projectId;
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
	result = prime * result
		+ ((projectId == null) ? 0 : projectId.hashCode());
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
	if (projectId == null) {
	    if (other.projectId != null)
		return false;
	} else if (!projectId.equals(other.projectId))
	    return false;
	return true;
    }

}
