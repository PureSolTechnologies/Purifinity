package com.puresoltechnologies.purifinity.server.metrics.entropy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorPluginInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class EntropyPluginRegistration extends AbstractPluginRegistration
	implements EvaluatorRemotePlugin {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
	    "metrics.plugin", "metrics.ejb", Evaluator.class,
	    EntropyMetricEvaluator.class);

    private static final EvaluatorPluginInformation INFORMATION = new EvaluatorPluginInformation(
	    EntropyMetric.ID, EntropyMetric.NAME, JNDI_ADDRESS,
	    EntropyMetric.DESCRIPTION,
	    EntropyMetric.EVALUATED_QUALITY_CHARACTERISTICS,
	    EntropyMetricEvaluatorParameter.ALL, EntropyMetric.DEPENDENCIES);

    @PostConstruct
    public void registration() {
	register(EvaluatorPluginServiceRemote.class,
		EvaluatorPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS,
		INFORMATION);
    }

    @PreDestroy
    public void unregistration() {
	unregister(EvaluatorPluginServiceRemote.class,
		EvaluatorPluginServiceRemote.JNDI_NAME, JNDI_ADDRESS);
    }

    @Override
    public String getName() {
	return EntropyMetric.NAME;
    }

}
