package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorRemoteService;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class NormalizedMaintainabilityIndexServiceRegistration extends
		AbstractServiceRegistration implements EvaluatorRemoteService {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			NormalizedMaintainabilityIndexEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			NormalizedMaintainabilityIndexEvaluator.ID,
			NormalizedMaintainabilityIndexEvaluator.NAME,
			NormalizedMaintainabilityIndexEvaluator.PLUGIN_VERSION,
			JNDI_ADDRESS,
			NormalizedMaintainabilityIndexEvaluator.DESCRIPTION,
			NormalizedMaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS,
			NormalizedMaintainabilityIndexEvaluatorParameter.ALL,
			NormalizedMaintainabilityIndexEvaluator.DEPENDENCIES);

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
		return NormalizedMaintainabilityIndexEvaluator.NAME;
	}

}
