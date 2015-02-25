package com.puresoltechnologies.purifinity.server.metrics.normmaint;

import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.MetricsPlugin;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class NormalizedMaintainabilityIndexServiceRegistration extends
	AbstractEvaluatorServiceRegistration {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
	    "metrics.plugin", "metrics.ejb", Evaluator.class,
	    NormalizedMaintainabilityIndexEvaluator.class);

    private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
	    NormalizedMaintainabilityIndexEvaluator.ID,
	    NormalizedMaintainabilityIndexEvaluator.NAME,
	    EvaluatorType.METRICS,
	    NormalizedMaintainabilityIndexEvaluator.PLUGIN_VERSION,
	    JNDI_ADDRESS,
	    NormalizedMaintainabilityIndexEvaluator.DESCRIPTION,
	    NormalizedMaintainabilityIndexEvaluator.PARAMETERS,
	    "/metrics.ui/normalized-maintainability/index.jsf",
	    "/metrics.ui/normalized-maintainability/config.jsf",
	    "/metrics.ui/normalized-maintainability/project.jsf",
	    "/metrics.ui/normalized-maintainability/run.jsf",
	    NormalizedMaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS,
	    NormalizedMaintainabilityIndexEvaluatorParameter.ALL,
	    NormalizedMaintainabilityIndexEvaluator.DEPENDENCIES);

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
	return NormalizedMaintainabilityIndexEvaluator.NAME;
    }

    @Override
    public EvaluatorServiceInformation getServiceInformation() {
	return INFORMATION;
    }
}
