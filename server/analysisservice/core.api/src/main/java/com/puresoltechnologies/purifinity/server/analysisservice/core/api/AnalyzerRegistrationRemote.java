package com.puresoltechnologies.purifinity.server.analysisservice.core.api;

import javax.naming.NamingException;

import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;

/**
 * This is the interface for the remote analyzer registration.
 */

public interface AnalyzerRegistrationRemote {

	public void registerRemote(String jndiAdress,
			AnalyzerInformation information) throws NamingException;

}
