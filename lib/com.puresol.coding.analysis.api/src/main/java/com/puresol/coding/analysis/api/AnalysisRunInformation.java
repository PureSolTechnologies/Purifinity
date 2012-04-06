package com.puresol.coding.analysis.api;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * This interface represents a single analysis run for a project.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface AnalysisRunInformation extends Serializable {

    /**
     * This method returns the UUID for this analysis. This UUID is used to load
     * the analysis from the underlying store.
     * 
     * @return An UUID object is returned.
     */
    public UUID getUUID();

    /**
     * This method returns the run start time stamp of the analysis.
     * 
     * @return A Date object is returned.
     */
    public Date getTime();

    /**
     * This method returns the time effort which was needed for analysis.
     * 
     * @return Returns the time effort in milliseconds.
     */
    public long getTimeOfRun();

    /**
     * This method returns a String with a simple description for the analysis.
     * 
     * @return A String object is returned containing the description.
     */
    public String getDescription();

}
