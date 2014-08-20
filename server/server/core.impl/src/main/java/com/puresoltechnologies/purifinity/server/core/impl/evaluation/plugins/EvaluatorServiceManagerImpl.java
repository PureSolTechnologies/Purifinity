package com.puresoltechnologies.purifinity.server.core.impl.evaluation.plugins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class EvaluatorServiceManagerImpl extends
		AbstractServiceManager<EvaluatorServiceInformation> implements
		EvaluatorServiceManager, EvaluatorServiceManagerRemote {

	@Inject
	private Logger logger;

	public EvaluatorServiceManagerImpl() {
		super("Evaluator Service Manager");
	}

	@Override
	public Evaluator createInstance(String jndi) {
		return JndiUtils.createRemoteEJBInstance(Evaluator.class, jndi);
	}

	@Override
	public Evaluator createInstanceById(String evaluatorId) {
		for (EvaluatorServiceInformation evaluator : getServices()) {
			if (evaluator.getId().equals(evaluatorId)) {
				return createInstance(evaluator.getJndiName());
			}
		}
		return null;
	}

	@Override
	public EvaluatorServiceInformation getEvaluatorPluginInformation(
			String evaluatorId) {
		for (EvaluatorServiceInformation evaluator : getServices()) {
			if (evaluator.getId().equals(evaluatorId)) {
				return evaluator;
			}
		}
		return null;
	}

	@Override
	public List<EvaluatorServiceInformation> getServicesSortedByDependency() {
		List<EvaluatorServiceInformation> sorted = new ArrayList<>();
		Collection<EvaluatorServiceInformation> services = getServices();
		for (EvaluatorServiceInformation service : services) {
			logger.warn("!!!!!!!! NO DEPENDENCY CHECK !!!!!!!!");
			// FIXME add sorting
			sorted.add(service);
		}
		return sorted;
	}
}