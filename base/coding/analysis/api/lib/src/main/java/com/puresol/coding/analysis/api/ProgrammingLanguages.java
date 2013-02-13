package com.puresol.coding.analysis.api;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import com.puresol.coding.lang.api.ProgrammingLanguage;

/**
 * This class is used to manage the ProgrammingLanguage services. This is the
 * central registry for all services to register themself to.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class ProgrammingLanguages {

	private static ProgrammingLanguages instance = null;

	public static ProgrammingLanguages getInstance() {
		if (instance == null) {
			loadInstance();
		}
		return instance;
	}

	private static synchronized void loadInstance() {
		ServiceLoader<ProgrammingLanguages> loader = ServiceLoader
				.load(ProgrammingLanguages.class);
		Iterator<ProgrammingLanguages> iterator = loader.iterator();
		if (!iterator.hasNext()) {
			throw new IllegalStateException("No implementation for '"
					+ ProgrammingLanguages.class.getName() + "' found.");
		}
		instance = iterator.next();
		if (iterator.hasNext()) {
			throw new IllegalStateException("Too many implementations for '"
					+ ProgrammingLanguages.class.getName() + "' found.");
		}
	}

	/**
	 * This method looks into the bundle context and returns all available
	 * programming languages.
	 * 
	 * @return
	 */
	public abstract List<AnalyzableProgrammingLanguage> getAll();

	/**
	 * This method is used to find a programming language by its name and
	 * version.
	 * 
	 * @param name
	 *            is the name of the programming language to be found.
	 * @return The programming language is returned. If the language was not
	 *         found null is returned.
	 */
	public ProgrammingLanguage findByName(String name, String version) {
		for (ProgrammingLanguage language : getAll()) {
			if ((language.getName().equals(name))
					&& (language.getVersion().equals(version))) {
				return language;
			}
		}
		return null;
	}
}
