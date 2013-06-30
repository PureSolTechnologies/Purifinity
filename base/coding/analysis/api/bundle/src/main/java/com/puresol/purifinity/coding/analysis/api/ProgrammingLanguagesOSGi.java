package com.puresol.purifinity.coding.analysis.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.purifinity.coding.analysis.api.AnalyzableProgrammingLanguage;
import com.puresol.purifinity.coding.analysis.api.ProgrammingLanguages;
import com.puresol.purifinity.coding.lang.api.ProgrammingLanguage;

/**
 * This class is used to manage the ProgrammingLanguage services. This is the
 * central registry for all services to register themself to.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProgrammingLanguagesOSGi extends ProgrammingLanguages {

    private static final Logger logger = LoggerFactory
	    .getLogger(ProgrammingLanguagesOSGi.class);

    private static final BundleContext context = Activator.getBundleContext();

    private final Collection<ServiceReference<AnalyzableProgrammingLanguage>> serviceReferences;

    public ProgrammingLanguagesOSGi() {
	super();
	try {
	    serviceReferences = context.getServiceReferences(
		    AnalyzableProgrammingLanguage.class, null);
	} catch (InvalidSyntaxException e) {
	    logger.error(e.getMessage(), e);
	    throw new RuntimeException(e.getMessage(), e);
	}
    }

    /**
     * This method looks into the bundle context and returns all available
     * programming languages.
     * 
     * @return
     */
    @Override
    public List<AnalyzableProgrammingLanguage> getAll() {
	List<AnalyzableProgrammingLanguage> services = new ArrayList<AnalyzableProgrammingLanguage>();
	for (ServiceReference<AnalyzableProgrammingLanguage> serviceReference : serviceReferences) {
	    services.add(context.getService(serviceReference));
	}
	return services;
    }

    /**
     * This method is used to find a programming language by its name and
     * version.
     * 
     * @param name
     *            is the name of the programming language to be found.
     * @return The programming language is returned. If the language was not
     *         found null is returned.
     */
    @Override
    public ProgrammingLanguage findByName(String name, String version) {
	for (ProgrammingLanguage language : getAll()) {
	    if ((language.getName().equals(name))
		    && (language.getVersion().equals(version))) {
		return language;
	    }
	}
	return null;
    }

    @Override
    public void close() throws IOException {
	for (ServiceReference<AnalyzableProgrammingLanguage> serviceReference : serviceReferences) {
	    context.ungetService(serviceReference);
	}
    }
}