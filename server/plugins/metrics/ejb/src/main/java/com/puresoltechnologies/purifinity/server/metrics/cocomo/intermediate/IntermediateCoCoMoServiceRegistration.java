package com.puresoltechnologies.purifinity.server.metrics.cocomo.intermediate;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.MetricsPlugin;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class IntermediateCoCoMoServiceRegistration extends
		AbstractEvaluatorServiceRegistration {

	private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
			"metrics.plugin", "metrics.ejb", Evaluator.class,
			IntermediateCoCoMoEvaluator.class);

	private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
			IntermediateCoCoMoEvaluator.ID, IntermediateCoCoMoEvaluator.NAME,
			EvaluatorType.METRICS, IntermediateCoCoMoEvaluator.PLUGIN_VERSION,
			JNDI_ADDRESS, IntermediateCoCoMoEvaluator.DESCRIPTION,
			IntermediateCoCoMoEvaluator.PARAMETERS,
			"/metrics.ui/intermediate-cocomo/index.jsf",
			"/metrics.ui/intermediate-cocomo/project.jsf",
			"/metrics.ui/intermediate-cocomo/run.jsf",
			IntermediateCoCoMoEvaluator.EVALUATED_QUALITY_CHARACTERISTICS,
			IntermediateCoCoMoEvaluatorParameter.ALL,
			IntermediateCoCoMoEvaluator.DEPENDENCIES);

	@Lock(LockType.WRITE)
	public void registration() {
		register(EvaluatorServiceManagerRemote.class,
				EvaluatorServiceManagerRemote.JNDI_NAME,
				MetricsPlugin.INFORMATION, JNDI_ADDRESS, INFORMATION);
	}

	@Lock(LockType.WRITE)
	public void unregistration() {
		unregister(EvaluatorServiceManagerRemote.class,
				EvaluatorServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
	}

	@Override
	@Lock(LockType.READ)
	public String getName() {
		return IntermediateCoCoMoEvaluator.NAME;
	}

	@Override
	@Lock(LockType.READ)
	public EvaluatorServiceInformation getServiceInformation() {
		return INFORMATION;
	}
}
