package com.puresol.coding.analysis.api;

import java.util.Date;
import java.util.UUID;

/**
 * This is the central interface for information about a single analysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface AnalysisInformation {

    /**
     * Returns the UUID for the analysis. This UUID is used to load the analysis
     * from the store.
     * 
     * @return An UUID object is returned.
     */
    public UUID getUUID();

    /**
     * This method returns a user readable name of the analysis.
     * 
     * @return A name is returned as String.
     */
    public String getName();

    /**
     * This method returns a user readable description.
     * 
     * @return A description is returned as String.
     */
    public String getDescription();

    /**
     * This method returns the time stamp of the creation of this analysis.
     * 
     * @return A {@link Date} object is returned containing the universal time
     *         stamp of the creation time.
     */
    public Date getCreationTime();

}
