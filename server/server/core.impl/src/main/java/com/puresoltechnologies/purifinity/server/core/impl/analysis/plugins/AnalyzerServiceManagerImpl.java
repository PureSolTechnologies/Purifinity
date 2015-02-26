package com.puresoltechnologies.purifinity.server.core.impl.analysis.plugins;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;

import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.server.common.plugins.AbstractServiceManager;
import com.puresoltechnologies.purifinity.server.common.plugins.EJBFacade;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManager;
import com.puresoltechnologies.purifinity.server.core.api.analysis.AnalyzerServiceManagerRemote;
import com.puresoltechnologies.purifinity.server.core.api.preferences.PreferencesStore;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

@Singleton
@EJBFacade
public class AnalyzerServiceManagerImpl extends
	AbstractServiceManager<AnalyzerServiceInformation> implements
	AnalyzerServiceManager, AnalyzerServiceManagerRemote {

    @Inject
    private PreferencesStore preferencesStore;

    private final Map<String, Boolean> analyzerActivations = new HashMap<>();

    public AnalyzerServiceManagerImpl() {
	super("Analyzer Service Manager");
    }

    @Override
    public ProgrammingLanguageAnalyzer createInstance(String jndi) {
	return JndiUtils.createRemoteEJBInstance(
		ProgrammingLanguageAnalyzer.class, jndi);
    }

    @Override
    public AnalyzerServiceInformation findByName(String languageName,
	    String languageVersion) {
	for (AnalyzerServiceInformation analyzerServiceInformation : getServices()) {
	    if (analyzerServiceInformation.getName().equals(languageName)
		    && analyzerServiceInformation.getVersion().equals(
			    languageVersion)) {
		return analyzerServiceInformation;
	    }
	}
	return null;
    }

    @Override
    @Lock(LockType.READ)
    public boolean isActive(String analyzerId) {
	Boolean active = analyzerActivations.get(analyzerId);
	if (active != null) {
	    return active;
	}
	active = preferencesStore.isActive(analyzerId);
	analyzerActivations.put(analyzerId, active);
	return active;
    }

    @Override
    @Lock(LockType.WRITE)
    public void setActive(String analyzerId, boolean active) {
	preferencesStore.setServiceActive(analyzerId, active);
	analyzerActivations.put(analyzerId, active);
    }

    @Override
    public ProgrammingLanguageAnalyzer createInstanceById(String analyzerId) {
	for (AnalyzerServiceInformation analyzer : getServices()) {
	    if (analyzer.getId().equals(analyzerId)) {
		return createInstance(analyzer.getJndiName());
	    }
	}
	return null;
    }
}
