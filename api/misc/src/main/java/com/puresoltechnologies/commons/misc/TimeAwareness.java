package com.puresoltechnologies.commons.misc;

import java.io.Serializable;
import java.util.Date;

/**
 * This interface is used to put some time information to an object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface TimeAwareness extends Serializable {

	/**
	 * This method returns the start time.
	 * 
	 * @return A {@link Date} object is returned containing the starting time.
	 */
	public Date getStartTime();

	/**
	 * This method returns the time duration of the run in milliseconds.
	 * 
	 * @return A long is returned containing the duration of the run in
	 *         milliseconds.
	 */
	public long getDuration();
}
