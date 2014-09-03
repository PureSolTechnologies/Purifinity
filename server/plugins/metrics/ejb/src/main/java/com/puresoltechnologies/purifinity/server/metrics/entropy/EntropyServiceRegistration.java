package com.puresoltechnologies.purifinity.server.metrics.entropy;

import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.MetricsPlugin;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class EntropyServiceRegistration extends
		AbstractEvaluatorServiceRegistration {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			EntropyMetricEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			EntropyMetric.ID, EntropyMetric.NAME, EvaluatorType.METRICS,
			EntropyMetric.PLUGIN_VERSION, JNDI_ADDRESS,
			EntropyMetric.DESCRIPTION, "/metrics.ui/entropy/index.jsf",
			"/metrics.ui/entropy/config.jsf",
			"/metrics.ui/entropy/project.jsf", "/metrics.ui/entropy/run.jsf",
			EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS,
			EntropyMetricEvaluatorParameter.ALL, EntropyMetric.DEPENDENCIES);

	public void registration() {
		register(EvaluatorServiceManagerRemote.class,
				EvaluatorServiceManagerRemote.JNDI_NAME,
				MetricsPlugin.INFORMATION, JNDI_ADDRESS, INFORMATION);
	}

	public void unregistration() {
		unregister(EvaluatorServiceManagerRemote.class,
				EvaluatorServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	public String getName() {
		return EntropyMetric.NAME;
	}

	@Override
	public EvaluatorServiceInformation getServiceInformation() {
		return INFORMATION;
	}
}
