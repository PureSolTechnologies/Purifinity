package com.puresoltechnologies.purifinity.server.metrics.sloc;

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
public class SLOCServiceRegistration extends
		AbstractEvaluatorServiceRegistration {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			SLOCEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			SLOCMetricCalculator.ID, SLOCMetricCalculator.NAME,
			EvaluatorType.METRICS, SLOCMetricCalculator.PLUGIN_VERSION,
			JNDI_ADDRESS, SLOCMetricCalculator.DESCRIPTION,
			"/metrics.ui/sloc/index.jsf", "/metrics.ui/sloc/config.jsf",
			"/metrics.ui/sloc/project.jsf", "/metrics.ui/sloc/run.jsf",
			SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS,
			SLOCEvaluatorParameter.ALL, SLOCMetricCalculator.DEPENDENCIES);

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
		return SLOCMetricCalculator.NAME;
	}

	@Override
	public EvaluatorServiceInformation getServiceInformation() {
		return INFORMATION;
	}
}
