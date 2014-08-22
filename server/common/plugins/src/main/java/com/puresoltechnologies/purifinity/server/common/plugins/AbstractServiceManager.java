package com.puresoltechnologies.purifinity.server.common.plugins;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.puresoltechnologies.purifinity.server.systemmonitor.events.EventLogger;

public abstract class AbstractServiceManager<ServiceInfo extends ServiceInformation>
		implements ServiceManager<ServiceInfo> {

	@Inject
	private Logger logger;

	@Inject
	private EventLogger eventLogger;

	private final Set<String> jndiAddresses = new HashSet<>();
	private final Map<String, ServiceInfo> services = new HashMap<>();
	private final Map<String, PluginInformation> plugins = new HashMap<>();

	private final String serviceManagerName;

	protected AbstractServiceManager(String serviceManagerName) {
		this.serviceManagerName = serviceManagerName;
	}

	@PostConstruct
	public void initialize() {
		eventLogger.logEvent(ServiceManagerEvents
				.createStartupEvent(serviceManagerName));
	}

	@PreDestroy
	public void shutdown() {
		// int count = 0;
		// while (plugins.size() > 0) {
		// count++;
		// if (count > 12) {
		// break;
		// }
		// try {
		// Thread.sleep(TimeUnit.SECONDS.toMillis(5));
		// } catch (InterruptedException e) {
		// break;
		// }
		// }
		jndiAddresses.clear();
		services.clear();
		eventLogger.logEvent(ServiceManagerEvents
				.createShutdownEvent(serviceManagerName));
	}

	@Override
	public void registerService(PluginInformation pluginInformation,
			String jndiName, ServiceInfo serviceInformation) {
		logger.info("Register new service '" + jndiName + "' from plugin '"
				+ pluginInformation.getName() + "'.");
		jndiAddresses.add(jndiName);

		services.put(jndiName, serviceInformation);
		plugins.put(jndiName, pluginInformation);
		logger.info("New service '" + jndiName + "' registered.");
	}

	@Override
	public void unregisterService(String jndiName) {
		services.remove(jndiName);
		plugins.remove(jndiName);
		jndiAddresses.remove(jndiName);
	}

	@Override
	public boolean hasServices() {
		return services.size() > 0;
	}

	@Override
	public Collection<ServiceInfo> getServices() {
		return Collections.unmodifiableCollection(services.values());
	}

	/**
	 * This method returns a list of all plugins which provide services which
	 * are registered already.
	 * 
	 * @return A {@link Collection} of {@link PluginInformation} is returned.
	 */
	@Override
	public Collection<PluginInformation> getPlugins() {
		Set<PluginInformation> plugins = new HashSet<>();
		plugins.addAll(this.plugins.values());
		return plugins;
	}
}
