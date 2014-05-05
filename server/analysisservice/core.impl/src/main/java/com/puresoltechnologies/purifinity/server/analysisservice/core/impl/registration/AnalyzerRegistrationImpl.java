package com.puresoltechnologies.purifinity.server.analysisservice.core.impl.registration;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerRegistration;
import com.puresoltechnologies.purifinity.server.analysisservice.core.api.AnalyzerRegistrationRemote;
import com.puresoltechnologies.purifinity.server.analysisservice.domain.AnalyzerInformation;
import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

@Singleton
@Local(AnalyzerRegistration.class)
@Remote(AnalyzerRegistrationRemote.class)
public class AnalyzerRegistrationImpl implements AnalyzerRegistration,
		AnalyzerRegistrationRemote {

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	public Set<String> internalJNDIAddresses = new HashSet<>();
	public Set<String> externalJNDIAddresses = new HashSet<>();
	public Map<String, AnalyzerInformation> languages = new HashMap<>();

	@PostConstruct
	public void initialize() {
		eventLogger.logEvent(AnalyzerRegistrationEvents.createStartupEvent());
	}

	@PreDestroy
	public void shutdown() {
		internalJNDIAddresses.clear();
		externalJNDIAddresses.clear();
		languages.clear();
		eventLogger.logEvent(AnalyzerRegistrationEvents.createShutdownEvent());
	}

	@Override
	public void registerInternal(String jndiAdress,
			AnalyzerInformation information) throws NamingException {
		logger.info("Register new analyzer: " + jndiAdress);
		internalJNDIAddresses.add(jndiAdress);

		languages.put(jndiAdress, information);
		logger.info("New analyzer '" + jndiAdress + "' registered.");
	}

	@Override
	public void registerRemote(String jndiAdress,
			AnalyzerInformation information) throws NamingException {
		logger.info("Register new analyzer: " + jndiAdress);
		externalJNDIAddresses.add(jndiAdress);

		languages.put(jndiAdress, information);
		logger.info("New analyzer '" + jndiAdress + "' registered.");
	}

	@Override
	public Collection<AnalyzerInformation> getAnalyzers() {
		return Collections.unmodifiableCollection(languages.values());
	}

}
