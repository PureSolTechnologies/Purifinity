package com.puresol.coding;

import java.io.File;
import java.util.List;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.uhura.ast.ParserTree;
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
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public Analyzer restoreAnalyzer(File file) throws PersistenceException {
		throw new PersistenceException(
				"Persistence not implemented in TestProgrammingLanguage!");
	}

	@Override
	public Analyzer createAnalyser(File file) {
		return null;
	}

	@Override
	public List<CodeRange> getAnalyzableCodeRanges(ParserTree parserTree) {
		return null;
	}

	@Override
	public <T> T getImplementation(Class<T> clazz) {
		return null;
	}

}
