package com.puresoltechnologies.purifinity.geronimo.test.arquillian;

import org.jboss.arquillian.container.test.spi.client.deployment.ApplicationArchiveProcessor;
import org.jboss.arquillian.core.spi.LoadableExtension;

public class ArquillianLifecycleExtension implements LoadableExtension {

	@Override
	public void register(ExtensionBuilder builder) {
		builder.observer(ArquillianLifecycle.class);
		builder.service(ApplicationArchiveProcessor.class,
				EnhancedArchiveProcessor.class);
	}
}