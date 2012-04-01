package com.puresol.coding.lang.fortran;

import java.util.Dictionary;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.analysis.api.ProgrammingLanguage;

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

    private ServiceRegistration<?> registration = null;

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting FortranLanguagePack...");

	Fortran fortran = Fortran.getInstance();
	fortran.setBundleContext(context);

	Dictionary<String, String> headers = context.getBundle().getHeaders();

	registration = context.registerService(ProgrammingLanguage.class,
		fortran, headers);

	logger.info("Started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
	logger.info("Stopping FortranLanguagePack...");
	registration.unregister();
	registration = null;
	logger.info("Stopped.");
    }

}
