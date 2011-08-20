package com.puresol.coding;

import java.util.List;

import org.apache.log4j.Logger;
import org.osgi.framework.InvalidSyntaxException;

import com.puresol.osgi.OSGiFrameworkManager;

/**
 * This class is used to manage the ProgrammingLanguage services.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProgrammingLanguages {

	private static final Logger logger = Logger
			.getLogger(ProgrammingLanguages.class);

	public static List<ProgrammingLanguage> getAll() {
		try {
			return OSGiFrameworkManager.getServices(
					ProgrammingLanguage.class.getName(), "(objectClass="
							+ ProgrammingLanguage.class.getName() + ")");
		} catch (InvalidSyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * This method is used to find a programming language by its name.
	 * 
	 * @param name
	 *            is the name of the programming language to be found.
	 * @return The programming language is returned. If the language was not
	 *         found null is returned.
	 */
	public static ProgrammingLanguage findByName(String name) {
		for (ProgrammingLanguage language : getAll()) {
			if (language.getName().equals(name)) {
				return language;
			}
		}
		return null;
	}
}
