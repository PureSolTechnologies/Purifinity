package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import javax.ejb.Remote;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorPluginInformation;

/**
 * This is the interface for the remote analyzer registration.
 */
@Remote
public interface EvaluatorPluginServiceRemote extends
		PluginService<EvaluatorPluginInformation> {

	public static final String JNDI_NAME = "java:global/server.app/server.core.impl/EvaluatorPluginServiceImpl!com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginServiceRemote";

}
