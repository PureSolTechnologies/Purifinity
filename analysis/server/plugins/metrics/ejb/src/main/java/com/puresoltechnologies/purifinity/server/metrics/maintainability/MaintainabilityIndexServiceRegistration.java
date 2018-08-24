package com.puresoltechnologies.purifinity.server.metrics.maintainability;

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
public class MaintainabilityIndexServiceRegistration extends AbstractEvaluatorServiceRegistration {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName("metrics.plugin",
	    "com-puresoltechnologies-purifinity-plugins-metrics.ejb", Evaluator.class,
	    MaintainabilityIndexEvaluator.class);

    private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
	    MaintainabilityIndexEvaluator.ID, MaintainabilityIndexEvaluator.NAME, EvaluatorType.METRICS,
	    MaintainabilityIndexEvaluator.PLUGIN_VERSION, JNDI_ADDRESS, MaintainabilityIndexEvaluator.DESCRIPTION,
	    MaintainabilityIndexEvaluator.PARAMETERS, "/metrics.ui/maintainability/index.jsf",
	    "/metrics.ui/maintainability/project.jsf", "/metrics.ui/maintainability/run.jsf",
	    MaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS, MaintainabilityIndexEvaluatorParameter.ALL,
	    MaintainabilityIndexEvaluator.DEPENDENCIES);

    @Lock(LockType.WRITE)
    public void registration() {
	register(EvaluatorServiceManagerRemote.class, EvaluatorServiceManagerRemote.JNDI_NAME,
		MetricsPlugin.INFORMATION, JNDI_ADDRESS, INFORMATION);
    }

    @Lock(LockType.WRITE)
    public void unregistration() {
	unregister(EvaluatorServiceManagerRemote.class, EvaluatorServiceManagerRemote.JNDI_NAME, JNDI_ADDRESS);
    }

    @Override
    @Lock(LockType.READ)
    public String getName() {
	return MaintainabilityIndexEvaluator.NAME;
    }

    @Override
    @Lock(LockType.READ)
    public EvaluatorServiceInformation getServiceInformation() {
	return INFORMATION;
    }
}
