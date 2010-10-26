package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.AbstractProgrammingLanguage;
import com.puresol.coding.analysis.Analyzer;

/**
 * This is the base class for Java Programming Language. The lexical and
 * syntactical information were taken out of "The Java™ Language Specification
 * -- Third Edition".
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Java extends AbstractProgrammingLanguage {

	private static final String[] FILE_SUFFIXES = { ".java" };

	private static Java instance = null;

	public static Java getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new Java();
		}
	}

	private Java() {
		super("Java");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isObjectOriented() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<? extends Analyzer> getAnalyserClass() {
		return JavaAnalyser.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Class<? extends TokenDefinition>> getKeywords() {
		return KEYWORDS;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Class<? extends TokenDefinition>> getLiterals() {
		return LITERALS;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Class<? extends TokenDefinition>> getSymbols() {
		return SYMBOLS;
	}

	@Override
	public Analyzer restoreAnalyzer(File file) throws PersistenceException {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Analyzer analyzer = (Analyzer) ois.readObject();
			ois.close();
			return analyzer;
		} catch (Throwable e) {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e1) {
				}
			}
			throw new PersistenceException(e);
		}
	}

}
