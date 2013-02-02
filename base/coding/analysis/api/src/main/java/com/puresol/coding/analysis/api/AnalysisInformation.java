package com.puresol.coding.analysis.api;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * This is the central interface for information about a single analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public final class AnalysisInformation implements Serializable {

    private static final long serialVersionUID = 1130436909310501842L;

    private final UUID uuid;
    private final String name;
    private final String description;
    private final Date creationTime;

    public AnalysisInformation(UUID uuid, String name, String description,
	    Date creationTime) {
	super();
	this.uuid = uuid;
	this.name = name;
	this.description = description;
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
     * This method returns a user readable name of the analysis.
     * 
     * @return A name is returned as String.
     */
    public final String getName() {
	return name;
    }

    /**
     * This method returns a user readable description.
     * 
     * @return A description is returned as String.
     */
    public final String getDescription() {
	return description;
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
		+ ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	AnalysisInformation other = (AnalysisInformation) obj;
	if (creationTime == null) {
	    if (other.creationTime != null)
		return false;
	} else if (!creationTime.equals(other.creationTime))
	    return false;
	if (description == null) {
	    if (other.description != null)
		return false;
	} else if (!description.equals(other.description))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (uuid == null) {
	    if (other.uuid != null)
		return false;
	} else if (!uuid.equals(other.uuid))
	    return false;
	return true;
    }

}
