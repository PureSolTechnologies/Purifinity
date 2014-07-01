package com.puresoltechnologies.purifinity.server.metrics.codedepth;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.codedepth.CodeDepthMetricEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorPluginInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class CodeDepthPluginRegistration extends AbstractPluginRegistration
	implements EvaluatorRemotePlugin {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
	    "metrics.plugin", "metrics.ejb", Evaluator.class,
	    CodeDepthMetricEvaluator.class);

    private static final EvaluatorPluginInformation INFORMATION = new EvaluatorPluginInformation(
	    CodeDepthMetric.ID, CodeDepthMetric.NAME,
	    CodeDepthMetric.PLUGIN_VERSION, JNDI_ADDRESS,
	    CodeDepthMetric.DESCRIPTION,
	    CodeDepthMetric.EVALUATED_QUALITY_CHARACTERISTICS,
	    CodeDepthMetricEvaluatorParameter.ALL, CodeDepthMetric.DEPENDENCIES);

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
	return CodeDepthMetric.NAME;
    }

}
