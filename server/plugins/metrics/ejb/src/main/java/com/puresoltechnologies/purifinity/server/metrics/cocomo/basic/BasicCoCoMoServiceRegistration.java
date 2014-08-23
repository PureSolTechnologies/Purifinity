package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorRemoteService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class BasicCoCoMoServiceRegistration extends AbstractServiceRegistration
		implements EvaluatorRemoteService {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			BasicCoCoMoEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			BasicCoCoMoEvaluator.ID, BasicCoCoMoEvaluator.NAME,
			BasicCoCoMoEvaluator.PLUGIN_VERSION, JNDI_ADDRESS,
			BasicCoCoMoEvaluator.DESCRIPTION, "/metrics.ui/cocomo/index.jsf",
			"/metrics.ui/cocomo/config.jsf", "/metrics.ui/cocomo/project.jsf",
			"/metrics.ui/cocomo/run.jsf",
			BasicCoCoMoEvaluator.EVALUATED_QUALITY_CHARACTERISTICS,
			BasicCoCoMoEvaluatorParameter.ALL,
			BasicCoCoMoEvaluator.DEPENDENCIES);

	@PostConstruct
	public void registration() {
		// FIXME
		// register(EvaluatorPluginServiceRemote.class,
		// EvaluatorPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS,
		// INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(EvaluatorServiceManagerRemote.class,
				EvaluatorServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return BasicCoCoMoEvaluator.NAME;
	}

}
