package com.puresoltechnologies.purifinity.server.common.job;

import java.io.Serializable;

/**
 * This interface is meant to be used for persistent user data on steps to
 * provide additional information about the current step running for progress
 * bars and monitoring.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface StepInformation extends Serializable {

	/**
	 * This method returns a name for the end user for the current step.
	 * 
	 * @return A {@link String} with the name is returned.
	 */
	public String getName();

	/**
	 * A description is provided here for the end user.
	 * 
	 * @return A {@link String} with the description is returned.
	 */
	public String getDescription();

}
