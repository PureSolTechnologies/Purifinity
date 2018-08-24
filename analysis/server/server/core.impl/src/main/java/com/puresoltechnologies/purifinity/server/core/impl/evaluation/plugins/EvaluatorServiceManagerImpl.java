package com.puresoltechnologies.purifinity.server.core.impl.evaluation.plugins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorInformation;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceManager;
import com.puresoltechnologies.purifinity.server.common.plugins.PluginInformation;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.EvaluatorServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesValue;
import com.puresoltechnologies.purifinity.server.core.impl.evaluation.store.EvaluatorStoreUtils;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
public class EvaluatorServiceManagerImpl extends AbstractServiceManager<EvaluatorServiceInformation, Evaluator>
	implements EvaluatorServiceManager, EvaluatorServiceManagerRemote {

    @Inject
    private Logger logger;

    @Inject
    private PreferencesStore preferencesStore;

    @Inject
    private EvaluatorStoreUtils evaluatorStoreUtils;

    private final Map<String, Boolean> analyzerActivations = new HashMap<>();

    public EvaluatorServiceManagerImpl() {
	super("Evaluator Service Manager");
    }

    @Override
    @Lock(LockType.WRITE)
    public void registerService(PluginInformation pluginInformation, String jndiName,
	    EvaluatorServiceInformation serviceInformation) {
	super.registerService(pluginInformation, jndiName, serviceInformation);
	addInformationToDatabase(pluginInformation, serviceInformation);
    }

    private void addInformationToDatabase(PluginInformation pluginInformation,
	    EvaluatorServiceInformation serviceInformation) {
	evaluatorStoreUtils.registerPluginInformation(pluginInformation, serviceInformation);
    }

    @Override
    @Lock(LockType.READ)
    public Evaluator createProxy(String jndi) {
	Evaluator evaluator = JndiUtils.createRemoteEJBInstance(Evaluator.class, jndi);
	EvaluatorInformation information = evaluator.getInformation();
	for (ConfigurationParameter<?> configurationParameter : evaluator.getConfigurationParameters()) {
	    PreferencesValue<?> value = preferencesStore.getPluginDefaultPreference(information.getId(),
		    configurationParameter);
	    if (value != null) {
		evaluator.setConfigurationParameter(configurationParameter, value.getValue());
	    }
	}
	return evaluator;
    }

    @Override
    @Lock(LockType.READ)
    public Evaluator createInstanceById(String evaluatorId) {
	for (EvaluatorServiceInformation evaluator : getServices()) {
	    if (evaluator.getId().equals(evaluatorId)) {
		return createProxy(evaluator.getJndiName());
	    }
	}
	return null;
    }

    @Override
    @Lock(LockType.READ)
    public EvaluatorServiceInformation getEvaluatorPluginInformation(String evaluatorId) {
	for (EvaluatorServiceInformation evaluator : getServices()) {
	    if (evaluator.getId().equals(evaluatorId)) {
		return evaluator;
	    }
	}
	return null;
    }

    @Override
    @Lock(LockType.READ)
    public List<EvaluatorServiceInformation> getServicesSortedByDependency() {
	return sortEvaluators(getServices());
    }

    private List<EvaluatorServiceInformation> sortEvaluators(Collection<EvaluatorServiceInformation> evaluators) {
	List<EvaluatorServiceInformation> sortedEvaluators = new ArrayList<>();
	Set<String> foundDependencies = new HashSet<>();
	boolean changed = true;
	while ((!evaluators.isEmpty()) && (changed)) {
	    changed = false;
	    Iterator<EvaluatorServiceInformation> evaluatorIterator = evaluators.iterator();
	    while (evaluatorIterator.hasNext()) {
		EvaluatorServiceInformation evaluator = evaluatorIterator.next();
		boolean ok = true;
		for (String dependency : evaluator.getDependencies()) {
		    if (!foundDependencies.contains(dependency)) {
			ok = false;
			break;
		    }
		}
		if (ok) {
		    sortedEvaluators.add(evaluator);
		    foundDependencies.add(evaluator.getId());
		    evaluatorIterator.remove();
		    changed = true;
		}
	    }
	}
	if (logger.isWarnEnabled()) {
	    StringBuilder builder = new StringBuilder();
	    for (EvaluatorServiceInformation evaluator : evaluators) {
		if (builder.length() > 0) {
		    builder.append(", ");
		}
		builder.append(evaluator.getName());
	    }
	    logger.warn(
		    "The following evaluators were not provided due to missing dependencies: " + builder.toString());
	}
	return sortedEvaluators;
    }

    @Override
    @Lock(LockType.READ)
    public boolean isActive(String evaluatorId) {
	Boolean active = analyzerActivations.get(evaluatorId);
	if (active != null) {
	    return active;
	}
	active = preferencesStore.isServiceActive(evaluatorId);
	analyzerActivations.put(evaluatorId, active);
	return active;
    }

    @Override
    @Lock(LockType.WRITE)
    public void setActive(String evaluatorId, boolean active) {
	preferencesStore.setServiceActive(evaluatorId, active);
	analyzerActivations.put(evaluatorId, active);
    }

    @Override
    @Lock(LockType.READ)
    public Evaluator getInstanceById(String evaluatorId) {
	for (EvaluatorServiceInformation evaluator : getServices()) {
	    if (evaluator.getId().equals(evaluatorId)) {
		return createProxy(evaluator.getJndiName());
	    }
	}
	return null;
    }
}
