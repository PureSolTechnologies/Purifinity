package com.puresol.coding.analysis.api;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.lang.api.ProgrammingLanguage;

/**
 * This class is used to manage the ProgrammingLanguage services. This is the
 * central registry for all services to register themself to.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProgrammingLanguages {

	private static final Logger logger = LoggerFactory
			.getLogger(ProgrammingLanguages.class);

	/**
	 * This method looks into the bundle context and returns all available
	 * programming languages.
	 * 
	 * @return
	 */
	public static List<AnalyzableProgrammingLanguage> getAll() {
		try {
			BundleContext context = Activator.getBundleContext();
			ServiceReference[] serviceReferences = context
					.getServiceReferences(ProgrammingLanguage.class.getName(),
							null);
			List<AnalyzableProgrammingLanguage> services = new ArrayList<AnalyzableProgrammingLanguage>();
			for (ServiceReference serviceReference : serviceReferences) {
				services.add((AnalyzableProgrammingLanguage) context
						.getService(serviceReference));
			}
			return services;
		} catch (InvalidSyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
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
	public static ProgrammingLanguage findByName(String name, String version) {
		for (ProgrammingLanguage language : getAll()) {
			if ((language.getName().equals(name))
					&& (language.getVersion().equals(version))) {
				return language;
			}
		}
		return null;
	}
}
