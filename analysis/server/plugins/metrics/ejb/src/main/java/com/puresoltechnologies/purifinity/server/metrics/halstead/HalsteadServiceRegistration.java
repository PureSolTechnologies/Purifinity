package com.puresoltechnologies.purifinity.server.metrics.halstead;

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
public class HalsteadServiceRegistration extends AbstractEvaluatorServiceRegistration {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName("metrics.plugin",
	    "com-puresoltechnologies-purifinity-plugins-metrics.ejb", Evaluator.class, HalsteadMetricEvaluator.class);

    private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(HalsteadMetric.ID,
	    HalsteadMetric.NAME, EvaluatorType.METRICS, HalsteadMetric.PLUGIN_VERSION, JNDI_ADDRESS,
	    HalsteadMetric.DESCRIPTION, HalsteadMetric.PARAMETERS, "/metrics.ui/halstead/index.jsf",
	    "/metrics.ui/halstead/project.jsf", "/metrics.ui/halstead/run.jsf",
	    HalsteadMetric.EVALUATED_QUALITY_CHARACTERISTICS, HalsteadMetricEvaluatorParameter.ALL,
	    HalsteadMetric.DEPENDENCIES);

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
	return HalsteadMetric.NAME;
    }

    @Override
    @Lock(LockType.READ)
    public EvaluatorServiceInformation getServiceInformation() {
	return INFORMATION;
    }
}
