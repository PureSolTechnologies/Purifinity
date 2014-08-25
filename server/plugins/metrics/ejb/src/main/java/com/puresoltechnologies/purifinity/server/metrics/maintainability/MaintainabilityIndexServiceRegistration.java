package com.puresoltechnologies.purifinity.server.metrics.maintainability;

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
public class MaintainabilityIndexServiceRegistration extends
		AbstractServiceRegistration implements EvaluatorRemoteService {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			MaintainabilityIndexEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			MaintainabilityIndexEvaluator.ID,
			MaintainabilityIndexEvaluator.NAME,
			MaintainabilityIndexEvaluator.PLUGIN_VERSION, JNDI_ADDRESS,
			MaintainabilityIndexEvaluator.DESCRIPTION,
			"/metrics.ui/maintainability/index.jsf",
			"/metrics.ui/maintainability/config.jsf",
			"/metrics.ui/maintainability/project.jsf",
			"/metrics.ui/maintainability/run.jsf",
			MaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS,
			MaintainabilityIndexEvaluatorParameter.ALL,
			MaintainabilityIndexEvaluator.DEPENDENCIES);

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
		return MaintainabilityIndexEvaluator.NAME;
	}

}
