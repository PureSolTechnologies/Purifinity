package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.plugins.ServiceManager;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

/**
 * This is the interface for the remote analyzer registration.
 */
@Remote
public interface AnalyzerServiceManagerRemote extends
		ServiceManager<AnalyzerServiceInformation> {

	public static final String JNDI_NAME = "java:global/server.app/server.core.impl/AnalyzerServiceManagerImpl!com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote";

}
