package com.puresoltechnologies.purifinity.server.core.api.analysis;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerInformation;

/**
 * This is the interface for the remote analyzer registration.
 */
public interface AnalyzerPluginServiceRemote extends
	PluginService<AnalyzerInformation> {

    public static final String JNDI_NAME = "java:global/server.app/server.core.impl/AnalyzerPluginServiceImpl!com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginServiceRemote";

}
