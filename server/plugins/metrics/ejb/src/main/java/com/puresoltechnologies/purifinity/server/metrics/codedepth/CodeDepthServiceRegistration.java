package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.MetricsPlugin;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class CodeDepthServiceRegistration extends
		AbstractEvaluatorServiceRegistration {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			CodeDepthMetricEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			CodeDepthMetric.ID, CodeDepthMetric.NAME, EvaluatorType.METRICS,
			CodeDepthMetric.PLUGIN_VERSION, JNDI_ADDRESS,
			CodeDepthMetric.DESCRIPTION, CodeDepthMetric.PARAMETERS,
			"/metrics.ui/codedepth/index.jsf",
			"/metrics.ui/codedepth/project.jsf",
			"/metrics.ui/codedepth/run.jsf",
			CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS,
			CodeDepthMetricEvaluatorParameter.ALL, CodeDepthMetric.DEPENDENCIES);

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
		return CodeDepthMetric.NAME;
	}

	@Override
	public EvaluatorServiceInformation getServiceInformation() {
		return INFORMATION;
	}
}
