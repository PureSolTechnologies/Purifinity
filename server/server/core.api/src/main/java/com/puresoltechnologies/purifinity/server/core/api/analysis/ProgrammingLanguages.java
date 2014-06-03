package com.puresoltechnologies.purifinity.server.core.api.analysis;

import java.io.Closeable;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import com.puresoltechnologies.commons.misc.Version;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguageAnalyzer;

/**
 * This class is used to manage the ProgrammingLanguage services. This is the
 * central registry for all services to register themself to.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class ProgrammingLanguages implements Closeable, AutoCloseable {

	public static ProgrammingLanguages createInstance() {
		ServiceLoader<ProgrammingLanguages> loader = ServiceLoader
				.load(ProgrammingLanguages.class);
		Iterator<ProgrammingLanguages> iterator = loader.iterator();
		if (!iterator.hasNext()) {
			throw new IllegalStateException("No implementation for '"
					+ ProgrammingLanguages.class.getName() + "' found.");
		}
		ProgrammingLanguages instance = iterator.next();
		if (iterator.hasNext()) {
			throw new IllegalStateException("Too many implementations for '"
					+ ProgrammingLanguages.class.getName() + "' found.");
		}
		return instance;
	}

	/**
	 * This method looks into the bundle context and returns all available
	 * programming languages.
	 * 
	 * @return
	 */
	public abstract List<ProgrammingLanguageAnalyzer> getAll();

	/**
	 * This method is used to find a programming language by its name and
	 * version.
	 * 
	 * @param name
	 *            is the name of the programming language to be found.
	 * @return The programming language is returned. If the language was not
	 *         found null is returned.
	 */
	public ProgrammingLanguage findByName(String name, Version version) {
		for (ProgrammingLanguage language : getAll()) {
			if ((language.getName().equals(name))
					&& (language.getVersion().equals(version))) {
				return language;
			}
		}
		return null;
	}

	@Override
	abstract public void close();
}
