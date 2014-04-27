package com.puresoltechnologies.purifinity.server.analysisservice.core.api;

import javax.naming.NamingException;

/**
 * This is the interface for the remote analyzer registration.
 */

public interface AnalyzerRegistrationRemote {

	public void registerRemote(String jndiAdress) throws NamingException;

}
