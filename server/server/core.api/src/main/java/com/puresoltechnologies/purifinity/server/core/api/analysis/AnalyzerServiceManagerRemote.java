package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Remote;

/**
 * This is the interface for the remote analyzer registration.
 */
@Remote
public interface AnalyzerServiceManagerRemote extends
		AnalyzerServiceManagerCommon {

	public static final String JNDI_NAME = "java:global/server.app/server.core.impl/AnalyzerServiceManagerImpl!com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote";

}
