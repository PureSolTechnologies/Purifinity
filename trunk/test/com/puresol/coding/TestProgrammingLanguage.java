package com.puresol.coding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.parser.tokens.TokenDefinition;
import com.puresol.utils.PersistenceException;

public class TestProgrammingLanguage extends AbstractProgrammingLanguage {

	private static final String[] FILE_SUFFIXES = { ".d" };

	private static TestProgrammingLanguage instance = null;

	public static TestProgrammingLanguage getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new TestProgrammingLanguage();
		}
	}

	private TestProgrammingLanguage() {
		super("Language");
	}

	@Override
	public boolean isObjectOriented() {
		return true;
	}

	@Override
	protected Class<? extends Analyzer> getAnalyserClass() {
		return null;
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public List<Class<? extends TokenDefinition>> getKeywords() {
		return new ArrayList<Class<? extends TokenDefinition>>();
	}

	@Override
	public List<Class<? extends TokenDefinition>> getLiterals() {
		return new ArrayList<Class<? extends TokenDefinition>>();
	}

	@Override
	public List<Class<? extends TokenDefinition>> getSymbols() {
		return new ArrayList<Class<? extends TokenDefinition>>();
	}

	@Override
	public Analyzer restoreAnalyzer(File file) throws PersistenceException {
		throw new PersistenceException(
				"Persistence not implemented in TestProgrammingLanguage!");
	}
}
