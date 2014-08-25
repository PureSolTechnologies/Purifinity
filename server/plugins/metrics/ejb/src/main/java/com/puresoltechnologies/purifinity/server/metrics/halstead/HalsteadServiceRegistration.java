package com.puresoltechnologies.purifinity.server.metrics.halstead;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorRemoteService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.MetricsPlugin;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class HalsteadServiceRegistration extends AbstractServiceRegistration
		implements EvaluatorRemoteService {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			HalsteadMetricEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			HalsteadMetric.ID, HalsteadMetric.NAME,
			HalsteadMetric.PLUGIN_VERSION, JNDI_ADDRESS,
			HalsteadMetric.DESCRIPTION, "/metrics.ui/halstead/index.jsf",
			"/metrics.ui/halstead/config.jsf",
			"/metrics.ui/halstead/project.jsf", "/metrics.ui/halstead/run.jsf",
			HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS,
			HalsteadMetricEvaluatorParameter.ALL, HalsteadMetric.DEPENDENCIES);

	@PostConstruct
	public void registration() {
		register(EvaluatorServiceManagerRemote.class,
				EvaluatorServiceManagerRemote.JNDI_NAME,
				MetricsPlugin.INFORMATION, JNDI_ADDRESS, INFORMATION);
	}

	@PreDestroy
	public void unregistration() {
		unregister(EvaluatorServiceManagerRemote.class,
				EvaluatorServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return HalsteadMetric.NAME;
	}

}
