package com.puresoltechnologies.purifinity.coding.lang.c11;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;

<<<<<<< HEAD
import com.puresoltechnologies.commons.ConfigurationParameter;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.coding.analysis.impl.AbstractProgrammingLanguageAnalyzer;
=======
import com.puresoltechnologies.commons.configuration.ConfigurationParameter;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.purifinity.coding.analysis.api.AbstractProgrammingLanguageAnalyzer;
import com.puresoltechnologies.purifinity.coding.analysis.api.CodeAnalyzer;
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9
import com.puresoltechnologies.purifinity.coding.lang.c11.grammar.C11Grammar;
import com.puresoltechnologies.purifinity.lang.api.LanguageGrammar;

/**
 * This is the base class for Java Programming Language. The lexical and
 * syntactical information were taken out of "The Java™ Language Specification
 * -- Third Edition".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class C11 extends AbstractProgrammingLanguageAnalyzer {

	public static final String[] FILE_SUFFIXES = { ".h", ".c" };

	private static final Set<ConfigurationParameter<?>> configurationParameters = new HashSet<>();

	private static C11 instance = null;

	public static C11 getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new C11();
		}
	}

	private C11() {
		super("C", "11");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public Set<ConfigurationParameter<?>> getAvailableConfigurationParameters() {
		return configurationParameters;
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					file));
			try {
				return (CodeAnalyzer) ois.readObject();
			} finally {
				ois.close();
			}
		} catch (ClassNotFoundException e) {
			/*
			 * XXX This needs to be null to go on with the language try out...
			 * :-(
			 */
			return null;
		}
	}

	@Override
	public CodeAnalyzer createAnalyser(CodeLocation source) {
		return new C11Analyzer(source);
	}

	@Override
	public LanguageGrammar getGrammar() {
		return C11Grammar.getInstance();
	}

	@Override
	public <T> T getImplementation(Class<T> clazz) {
		ServiceLoader<T> service = ServiceLoader.load(clazz);
		Iterator<T> iterator = service.iterator();
		T result = iterator.next();
		if (iterator.hasNext()) {
			throw new RuntimeException(
					"There is more than one implementation available for '"
							+ clazz.getName() + "'!");
		}
		return result;
	}
}
