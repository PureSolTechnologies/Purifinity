package com.puresol.coding.metrics;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.metrics.cocomo.CoCoMoActivator;
import com.puresol.coding.metrics.codedepth.CodeDepthActivator;
import com.puresol.coding.metrics.halstead.HalsteadActivator;
import com.puresol.coding.metrics.mccabe.McCabeActivator;
import com.puresol.coding.metrics.sloc.SLOCActivator;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator implements BundleActivator {

    private static final Logger logger = LoggerFactory
	    .getLogger(Activator.class);

    private static BundleContext bundleContext = null;

    public static BundleContext getBundleContext() {
	return bundleContext;
    }

    private final List<BundleActivator> bundleActivators = new ArrayList<BundleActivator>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting Metrics Base Package...");

	if (Activator.bundleContext == null) {
	    Activator.bundleContext = context;
	} else {
	    throw new RuntimeException("Context should be not set already!");
	}

	bundleActivators.add(new CoCoMoActivator());
	bundleActivators.add(new CodeDepthActivator());
	// TODO bundleActivators.add(new EntropyActivator());
	bundleActivators.add(new HalsteadActivator());
	// TODO bundleActivators.add(new MaintainabilityActivator());
	bundleActivators.add(new McCabeActivator());
	// TODO bundleActivators.add(new NormalizedMaintainabilityActivator());
	bundleActivators.add(new SLOCActivator());
	for (BundleActivator activator : bundleActivators) {
	    activator.start(context);
	}

	logger.info("Started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping Metrics Base Package...");

	for (BundleActivator activator : bundleActivators) {
	    activator.stop(context);
	}
	bundleActivators.clear();

	if (Activator.bundleContext != null) {
	    Activator.bundleContext = null;
	} else {
	    throw new RuntimeException("Context should be set already!");
	}

	logger.info("Stopped.");
    }

}
