package com.puresoltechnologies.purifinity.server.analysisservice.core.api;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;

/**
 * This is the interface for the remote analyzer registration.
 */
@Remote
public interface AnalyzerPluginServiceRemote extends
		PluginService<AnalyzerInformation> {

	public static final String JNDI_NAME = "java:global/analysisservice.app/analysisservice.core.impl/AnalyzerPluginServiceImpl!com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerPluginServiceRemote";

}
