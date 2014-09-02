package com.puresoltechnologies.purifinity.server.metrics.mccabe;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.MetricsPlugin;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
@EJBFacade
public class McCabeServiceRegistration extends
		AbstractEvaluatorServiceRegistration {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			McCabeMetricEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			McCabeMetric.ID, McCabeMetric.NAME, EvaluatorType.METRICS,
			McCabeMetric.PLUGIN_VERSION, JNDI_ADDRESS,
			McCabeMetric.DESCRIPTION, "/metrics.ui/mccabe/index.jsf",
			"/metrics.ui/mccabe/config.jsf", "/metrics.ui/mccabe/project.jsf",
			"/metrics.ui/mccabe/run.jsf",
			McCabeMetric.EVALUATED_QUALITY_CHARACTERISTICS,
			McCabeMetricEvaluatorParameter.ALL, McCabeMetric.DEPENDENCIES);

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
		return McCabeMetric.NAME;
	}

	@Override
	public EvaluatorServiceInformation getServiceInformation() {
		return INFORMATION;
	}
}
