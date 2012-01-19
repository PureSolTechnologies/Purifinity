package com.puresol.osgi;

import java.util.List;

import org.osgi.framework.InvalidSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BundleConfiguratorManager {

    private static final Logger logger = LoggerFactory
	    .getLogger(BundleConfiguratorManager.class);

    public static List<BundleConfigurator> getAll() {
	try {
	    return OSGiFrameworkManager.getServices(
		    BundleConfigurator.class.getName(), "(objectClass="
			    + BundleConfigurator.class.getName() + ")");
	} catch (InvalidSyntaxException e) {
	    logger.error(e.getMessage(), e);
	    throw new RuntimeException(e.getMessage(), e);
	}
    }

}
