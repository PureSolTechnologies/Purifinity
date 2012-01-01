package com.puresol.coding.lang.fortran;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.config.APIInformation;

/**
 * This class is used as OSGi bundle activator. This class only registers and
 * unregisters itself int the central ProgrammingLanguages object.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Activator implements BundleActivator {

    private static final Logger logger = Logger.getLogger(Activator.class);

    private ServiceRegistration<?> registration = null;

    @Override
    public void start(BundleContext context) throws Exception {
	logger.info("Starting FortranLanguagePack...");

	Fortran fortran = Fortran.getInstance();
	fortran.setBundleContext(context);

	Dictionary<String, Object> properties = new Hashtable<String, Object>();
	properties.put("service.name", fortran.getName());
	properties.put("service.description", fortran.getName());
	properties.put("service.vendor", APIInformation.getPackageOwner());

	registration = context.registerService(
		ProgrammingLanguage.class.getName(), fortran, properties);

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
