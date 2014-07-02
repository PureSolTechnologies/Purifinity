package com.puresoltechnologies.purifinity.server.core.impl.evaluation.plugins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Singleton;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractPluginService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginService;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorPluginServiceRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorPluginInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class EvaluatorPluginServiceImpl extends
	AbstractPluginService<EvaluatorPluginInformation> implements
	EvaluatorPluginService, EvaluatorPluginServiceRemote {

    public EvaluatorPluginServiceImpl() {
	super("Evaluator Plugin Service");
    }

    @Override
    public Evaluator createInstance(String jndi) {
	return JndiUtils.createRemoteEJBInstance(Evaluator.class, jndi);
    }

    @Override
    public List<EvaluatorPluginInformation> getServicesSortedByDependency() {
	List<EvaluatorPluginInformation> sorted = new ArrayList<>();
	Collection<EvaluatorPluginInformation> services = getServices();
	for (EvaluatorPluginInformation service : services) {
	    // FIXME add sorting
	    sorted.add(service);
	}
	return sorted;
    }
}
