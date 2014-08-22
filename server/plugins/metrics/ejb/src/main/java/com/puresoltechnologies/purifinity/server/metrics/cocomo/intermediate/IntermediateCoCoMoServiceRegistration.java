package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

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
public class IntermediateCoCoMoServiceRegistration extends
		AbstractServiceRegistration implements EvaluatorRemoteService {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			IntermediateCoCoMoEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			IntermediateCoCoMoEvaluator.ID, IntermediateCoCoMoEvaluator.NAME,
			IntermediateCoCoMoEvaluator.PLUGIN_VERSION, JNDI_ADDRESS,
			IntermediateCoCoMoEvaluator.DESCRIPTION,
			"/metrics.ui/intermediate-cocomo/index",
			"/metrics.ui/intermediate-cocomo/config",
			"/metrics.ui/intermediate-cocomo/project",
			"/metrics.ui/intermediate-cocomo/run",
			IntermediateCoCoMoEvaluator.EVALUATED_QUALITY_CHARACTERISTICS,
			IntermediateCoCoMoEvaluatorParameter.ALL,
			IntermediateCoCoMoEvaluator.DEPENDENCIES);

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
		return IntermediateCoCoMoEvaluator.NAME;
	}

}
