package com.puresoltechnologies.purifinity.server.core.impl.evaluation.plugins;

import javax.ejb.Singleton;
import javax.naming.NamingException;

import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorPluginInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class EvaluatorPluginServiceImpl extends
	AbstractPluginService<EvaluatorPluginInformation> implements
	EvaluatorPluginService, EvaluatorPluginServiceRemote {

    public EvaluatorPluginServiceImpl() {
	super("Evaluator Plugin Service");
    }

    @Override
    public EvaluatorRemotePlugin createInstance(String jndi)
	    throws NamingException {
	return JndiUtils.createRemoteEJBInstance(EvaluatorRemotePlugin.class,
		jndi);
    }

}
