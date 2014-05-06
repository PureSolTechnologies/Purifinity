package com.puresoltechnologies.purifinity.server.common.plugins;

import java.io.Serializable;
import java.util.Collection;
import java.util.Properties;

public interface PluginService<PluginInfo extends Serializable> {

	public void registerService(String jndiName, PluginInfo pluginInformation,
			Properties properties, Class<?>... interfaces);

	public void unregisterService(String jndiName);

	public Collection<PluginInfo> getServices();

}
