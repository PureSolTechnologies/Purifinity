package com.puresoltechnologies.purifinity.server.analysisservice.core.api;

import java.util.Collection;

import javax.naming.NamingException;

import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;

/**
 * This is the interface for the internal analyzer registration.
 */
public interface AnalyzerRegistration {

	public void registerInternal(String jndiAdress,
			AnalyzerInformation information) throws NamingException;

	public Collection<AnalyzerInformation> getAnalyzers();
}
