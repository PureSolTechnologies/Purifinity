package com.puresol.coding.analysis.api;

import java.io.Serializable;
import java.util.Date;

/**
 * This interface is used to put some time aware information to an object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface TimeAwareness extends Serializable {

    /**
     * This method returns the start time.
     * 
     * @return
     */
    public Date getTime();

    /**
     * This method returns the time duration of the run in milliseconds.
     * 
     * @return
     */
    public long getTimeOfRun();
}