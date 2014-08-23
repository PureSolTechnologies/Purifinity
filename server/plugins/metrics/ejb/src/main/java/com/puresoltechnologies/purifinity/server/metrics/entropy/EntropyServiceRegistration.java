package com.puresoltechnologies.purifinity.server.metrics.entropy;

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
public class EntropyServiceRegistration extends AbstractServiceRegistration
		implements EvaluatorRemoteService {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			EntropyMetricEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			EntropyMetric.ID, EntropyMetric.NAME, EntropyMetric.PLUGIN_VERSION,
			JNDI_ADDRESS, EntropyMetric.DESCRIPTION,
			"/metrics.ui/entropy/index.jsf", "/metrics.ui/entropy/config.jsf",
			"/metrics.ui/entropy/project.jsf", "/metrics.ui/entropy/run.jsf",
			EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS,
			EntropyMetricEvaluatorParameter.ALL, EntropyMetric.DEPENDENCIES);

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
		return EntropyMetric.NAME;
	}

}
