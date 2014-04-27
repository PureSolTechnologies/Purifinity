package com.puresoltechnologies.purifinity.server.analysisservice.core.api;

import java.util.Collection;

import javax.naming.NamingException;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;

/**
 * This is the interface for the internal analyzer registration.
 */
public interface AnalyzerRegistration {

	public void registerInternal(String jndiAdress) throws NamingException;

	public Collection<ProgrammingLanguage> getAnalyzers();
}
