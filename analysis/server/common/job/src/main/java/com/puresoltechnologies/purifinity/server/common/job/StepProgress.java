package com.puresoltechnologies.purifinity.server.common.job;

import java.io.Serializable;

/**
 * This interface is used for persistent user data on steps to provide
 * information about the progress.
 * 
 * @author Rick-Rainer Ludwig
 *
 */
public interface StepProgress extends Serializable {

	/**
	 * Returns the number of the current item.
	 * 
	 * @return A long is returned.
	 */
	public long getCurrentItem();

	/**
	 * This method returns the total number of items and therefore the total
	 * amount of work.
	 * 
	 * @return A long is returned.
	 */
	public long getTotalItems();

}
