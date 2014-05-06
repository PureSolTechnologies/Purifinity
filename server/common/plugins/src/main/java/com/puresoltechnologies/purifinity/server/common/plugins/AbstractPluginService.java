package com.puresoltechnologies.purifinity.server.common.plugins;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

public abstract class AbstractPluginService<PluginInfo extends Serializable>
		implements PluginService<PluginInfo> {

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	private final Set<String> jndiAddresses = new HashSet<>();
	private final Map<String, PluginInfo> languages = new HashMap<>();

	private final String pluginServiceName;

	protected AbstractPluginService(String pluginServiceName) {
		this.pluginServiceName = pluginServiceName;
	}

	@PostConstruct
	public void initialize() {
		eventLogger.logEvent(PluginServiceEvents
				.createStartupEvent(pluginServiceName));
	}

	@PreDestroy
	public void shutdown() {
		jndiAddresses.clear();
		languages.clear();
		eventLogger.logEvent(PluginServiceEvents
				.createShutdownEvent(pluginServiceName));
	}

	@Override
	public void registerService(String jndiName, PluginInfo pluginInformation,
			Properties properties, Class<?>... interfaces) {
		logger.info("Register new analyzer: " + jndiName);
		jndiAddresses.add(jndiName);

		languages.put(jndiName, pluginInformation);
		logger.info("New analyzer '" + jndiName + "' registered.");
	}

	@Override
	public void unregisterService(String jndiName) {
		languages.remove(jndiName);
		jndiAddresses.remove(jndiName);
	}

	@Override
	public Collection<PluginInfo> getServices() {
		return Collections.unmodifiableCollection(languages.values());
	}

}