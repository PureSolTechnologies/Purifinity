package com.puresoltechnologies.purifinity.server.metrics.maintainability;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.api.maintainability.MaintainabilityIndexEvaluatorParameter;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginRegistration;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorRemotePlugin;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorPluginInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@Startup
public class MaintainabilityIndexPluginRegistration extends
	AbstractPluginRegistration implements EvaluatorRemotePlugin {

    private static final String JNDI_ADDRESS = JndiUtils.createGlobalName(
	    "metrics.plugin", "metrics.ejb", Evaluator.class,
	    MaintainabilityIndexEvaluator.class);

    private static final EvaluatorPluginInformation INFORMATION = new EvaluatorPluginInformation(
	    MaintainabilityIndexEvaluator.ID,
	    MaintainabilityIndexEvaluator.NAME,
	    MaintainabilityIndexEvaluator.PLUGIN_VERSION, JNDI_ADDRESS,
	    MaintainabilityIndexEvaluator.DESCRIPTION,
	    MaintainabilityIndexEvaluator.EVALUATED_QUALITY_CHARACTERISTICS,
	    MaintainabilityIndexEvaluatorParameter.ALL_FILE,
	    MaintainabilityIndexEvaluator.DEPENDENCIES);

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
	return MaintainabilityIndexEvaluator.NAME;
    }

}
