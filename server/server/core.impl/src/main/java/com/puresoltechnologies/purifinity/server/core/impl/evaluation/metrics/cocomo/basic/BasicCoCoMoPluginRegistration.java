package com.puresoltechnologies.purifinity.server.core.impl.evaluation.metrics.cocomo.basic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class BasicCoCoMoPluginRegistration extends AbstractPluginRegistration
		implements EvaluatorRemotePlugin {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalAddress(
			"server.app", "server.core.impl", EvaluatorRemotePlugin.class,
			BasicCoCoMoPluginRegistration.class);

	private static final EvaluatorInformation INFORMATION = new EvaluatorInformation(
			BasicCoCoMoEvaluator.NAME, JNDI_ADDRESS,
			BasicCoCoMoEvaluator.DESCRIPTION);

	@PostConstruct
	public void registraion() {
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
		return BasicCoCoMoEvaluator.NAME;
	}

}
