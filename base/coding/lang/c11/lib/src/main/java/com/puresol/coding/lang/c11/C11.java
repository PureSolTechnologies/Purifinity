package com.puresol.coding.lang.c11;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.ServiceLoader;

import com.puresol.coding.analysis.api.CodeAnalyzer;
import com.puresol.coding.analysis.impl.AbstractProgrammingLanguage;
import com.puresol.coding.lang.api.LanguageGrammar;
import com.puresol.coding.lang.c11.grammar.C11Grammar;
import com.puresol.uhura.source.CodeLocation;

/**
 * This is the base class for Java Programming Language. The lexical and
 * syntactical information were taken out of "The Java™ Language Specification
 * -- Third Edition".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class C11 extends AbstractProgrammingLanguage {

	private static final String[] FILE_SUFFIXES = { ".java" };

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

	public C11() {
		super("Java", "1.6");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
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
