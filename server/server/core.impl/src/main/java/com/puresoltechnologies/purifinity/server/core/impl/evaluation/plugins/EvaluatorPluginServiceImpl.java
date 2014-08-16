package com.puresoltechnologies.purifinity.server.core.impl.evaluation.plugins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

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

    @Inject
    private Logger logger;

    public EvaluatorPluginServiceImpl() {
	super("Evaluator Plugin Service");
    }

    @Override
    public Evaluator createInstance(String jndi) {
	return JndiUtils.createRemoteEJBInstance(Evaluator.class, jndi);
    }

    @Override
    public Evaluator createInstanceById(String evaluatorId) {
	for (EvaluatorPluginInformation evaluator : getServices()) {
	    if (evaluator.getId().equals(evaluatorId)) {
		return createInstance(evaluator.getJndiName());
	    }
	}
	return null;
    }

    @Override
    public EvaluatorPluginInformation getEvaluatorPluginInformation(
	    String evaluatorId) {
	for (EvaluatorPluginInformation evaluator : getServices()) {
	    if (evaluator.getId().equals(evaluatorId)) {
		return evaluator;
	    }
	}
	return null;
    }

    @Override
    public List<EvaluatorPluginInformation> getServicesSortedByDependency() {
	List<EvaluatorPluginInformation> sorted = new ArrayList<>();
	Collection<EvaluatorPluginInformation> services = getServices();
	for (EvaluatorPluginInformation service : services) {
	    logger.warn("!!!!!!!! NO DEPENDENCY CHECK !!!!!!!!");
	    // FIXME add sorting
	    sorted.add(service);
	}
	return sorted;
    }
}
