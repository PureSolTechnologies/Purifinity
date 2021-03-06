package com.puresoltechnologies.purifinity.server.metrics.sloc;

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
public class SLOCServiceRegistration extends AbstractEvaluatorServiceRegistration {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName("metrics.plugin",
	    "com-puresoltechnologies-purifinity-plugins-metrics.ejb", Evaluator.class, SLOCEvaluator.class);

    private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
	    SLOCMetricCalculator.ID, SLOCMetricCalculator.NAME, EvaluatorType.METRICS,
	    SLOCMetricCalculator.PLUGIN_VERSION, JNDI_ADDRESS, SLOCMetricCalculator.DESCRIPTION,
	    SLOCMetricCalculator.PARAMETERS, "/metrics.ui/sloc/index.jsf", "/metrics.ui/sloc/project.jsf",
	    "/metrics.ui/sloc/run.jsf", SLOCMetricCalculator.EVALUATED_QUALITY_CHARACTERISTICS,
	    SLOCEvaluatorParameter.ALL, SLOCMetricCalculator.DEPENDENCIES);

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
	return SLOCMetricCalculator.NAME;
    }

    @Override
    @Lock(LockType.READ)
    public EvaluatorServiceInformation getServiceInformation() {
	return INFORMATION;
    }
}
