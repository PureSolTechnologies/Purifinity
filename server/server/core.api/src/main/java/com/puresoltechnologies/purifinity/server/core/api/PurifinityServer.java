package com.puresoltechnologies.purifinity.server.core.api;

import com.puresoltechnologies.purifinity.server.domain.PurifinityServerStatus;


/**
 * This is the central API for the Purifinity server implementation.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface PurifinityServer {

	/**
	 * This method returns the current server status with its status
	 * information.
	 * 
	 * @return A {@link PurifinityServerStatus} object is returned containing
	 *         the status information.
	 */
	public PurifinityServerStatus getStatus();

}
