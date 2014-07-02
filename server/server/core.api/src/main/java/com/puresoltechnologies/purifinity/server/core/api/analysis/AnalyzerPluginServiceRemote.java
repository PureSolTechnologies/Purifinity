package com.puresoltechnologies.purifinity.server.core.api.analysis;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerPluginInformation;

/**
 * This is the interface for the remote analyzer registration.
 */
@Remote
public interface AnalyzerPluginServiceRemote extends
	PluginService<AnalyzerPluginInformation> {

    public static final String JNDI_NAME = "java:global/server.app/server.core.impl/AnalyzerPluginServiceImpl!com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerPluginServiceRemote";

}
