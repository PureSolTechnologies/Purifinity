package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class MaintainabilityIndexPluginRegistration extends
		AbstractPluginRegistration implements EvaluatorRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			MaintainabilityIndexEvaluator.class);

	private static final EvaluatorInformation INFORMATION = new EvaluatorInformation(
			MaintainabilityIndexEvaluator.NAME, JNDI_ADDRESS,
			MaintainabilityIndexEvaluator.DESCRIPTION);

	@PostConstruct
	public void registration() {
		register(EvaluatorPluginServiceRemote.class,
				EvaluatorPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS,
				INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(EvaluatorPluginServiceRemote.class,
				EvaluatorPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return MaintainabilityIndexEvaluator.NAME;
	}

}
