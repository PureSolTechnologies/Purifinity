package com.puresoltechnologies.purifinity.server.analysisservice.core.api;

import java.util.Collection;

import javax.naming.NamingException;

/**
 * This is the interface for the internal analyzer registration.
 */
public interface AnalyzerRegistration {

	public void registerInternal(String jndiAdress) throws NamingException;

	public Collection<AnalyzerRemotePlugin> getAnalyzers();
}
