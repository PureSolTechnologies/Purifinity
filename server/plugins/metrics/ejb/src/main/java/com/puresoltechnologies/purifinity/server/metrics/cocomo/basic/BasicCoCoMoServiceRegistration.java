package com.puresoltechnologies.purifinity.server.metrics.cocomo.basic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluatorServiceRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.metrics.MetricsPlugin;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class BasicCoCoMoServiceRegistration extends
	AbstractEvaluatorServiceRegistration {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
	    "metrics.plugin", "metrics.ejb", Evaluator.class,
	    BasicCoCoMoEvaluator.class);

    private static final EvaluatorServiceInformation INFORMATION = new EvaluatorServiceInformation(
	    BasicCoCoMoEvaluator.ID, BasicCoCoMoEvaluator.NAME,
	    EvaluatorType.METRICS, BasicCoCoMoEvaluator.PLUGIN_VERSION,
	    JNDI_ADDRESS, BasicCoCoMoEvaluator.DESCRIPTION,
	    BasicCoCoMoEvaluator.PARAMETERS, "/metrics.ui/cocomo/index.jsf",
	    "/metrics.ui/cocomo/config.jsf", "/metrics.ui/cocomo/project.jsf",
	    "/metrics.ui/cocomo/run.jsf",
	    BasicCoCoMoEvaluator.EVALUATED_QUALITY_CHARACTERISTICS,
	    BasicCoCoMoEvaluatorParameter.ALL,
	    BasicCoCoMoEvaluator.DEPENDENCIES);

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
	return BasicCoCoMoEvaluator.NAME;
    }

    @Override
    public EvaluatorServiceInformation getServiceInformation() {
	return INFORMATION;
    }
}
