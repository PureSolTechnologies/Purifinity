package com.puresol.coding.metrics;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.metrics.cocomo.CoCoMoActivator;
import com.puresol.coding.metrics.codedepth.CodeDepthActivator;
import com.puresol.coding.metrics.entropy.EntropyActivator;
import com.puresol.coding.metrics.halstead.HalsteadActivator;
import com.puresol.coding.metrics.maintainability.MaintainabilityActivator;
import com.puresol.coding.metrics.mccabe.McCabeActivator;
import com.puresol.coding.metrics.normmaint.NormalizedMaintainabilityActivator;
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

    private final List<BundleActivator> bundleActivators = new ArrayList<BundleActivator>();

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting Metrics Base Package...");

	bundleActivators.add(new CoCoMoActivator());
	bundleActivators.add(new CodeDepthActivator());
	bundleActivators.add(new EntropyActivator());
	bundleActivators.add(new HalsteadActivator());
	bundleActivators.add(new MaintainabilityActivator());
	bundleActivators.add(new McCabeActivator());
	bundleActivators.add(new NormalizedMaintainabilityActivator());
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

	logger.info("Stopped.");
    }

}
