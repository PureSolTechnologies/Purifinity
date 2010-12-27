package com.puresol.gui.osgi;

import java.util.List;

import org.apache.log4j.Logger;
import org.osgi.framework.InvalidSyntaxException;

import com.puresol.osgi.OSGiFrameworkManager;

public class BundleConfiguratorManager {

	private static final Logger logger = Logger
			.getLogger(BundleConfiguratorManager.class);

	public static List<BundleConfigurator> getAll(String frameworkName) {
		try {
			return OSGiFrameworkManager.getServices(frameworkName,
					BundleConfigurator.class.getName(), "(objectClass="
							+ BundleConfigurator.class.getName() + ")",
					BundleConfigurator.class);
		} catch (InvalidSyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
