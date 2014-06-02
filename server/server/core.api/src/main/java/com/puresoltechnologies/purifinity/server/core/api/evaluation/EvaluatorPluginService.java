package com.puresoltechnologies.purifinity.server.core.api.evaluation;

import javax.ejb.Local;
import javax.naming.NamingException;

import com.puresoltechnologies.purifinity.server.common.plugins.PluginService;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorInformation;

/**
 * This is the interface for the internal analyzer registration.
 */
@Local
public interface EvaluatorPluginService extends
		PluginService<EvaluatorInformation> {

	public EvaluatorRemotePlugin createInstance(String jndi)
			throws NamingException;

}
