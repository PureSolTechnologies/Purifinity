package com.puresoltechnologies.purifinity.server.test.lang;

import java.io.File;
import java.io.IOException;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.ust.AbstractProduction;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.terminal.AbstractTerminal;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerStore;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageGrammar;
import com.puresoltechnologies.purifinity.analysis.domain.HalsteadSymbol;
import com.puresoltechnologies.purifinity.analysis.domain.SLOCType;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractProgrammingLanguage;
import com.puresoltechnologies.purifinity.server.test.lang.grammar.TestLanguageGrammar;

/**
 * This is a test programming languages which is used as a mock up for real
 * languages.
 * 
 * This language is strictly for testing purposes only!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguage extends AbstractProgrammingLanguage implements
		AnalyzerStore {

	private static final String[] FILE_SUFFIXES = { ".d" };

	private static TestLanguage instance = null;

	public static TestLanguage getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new TestLanguage();
		}
	}

	private TestLanguage() {
		super("TestLanguage", "0.0.0");
	}

	@Override
	protected String[] getValidFileSuffixes() {
		return FILE_SUFFIXES;
	}

	@Override
	public CodeAnalyzer restoreAnalyzer(File file) throws IOException {
		throw new IOException(
				"Persistence not implemented in TestProgrammingLanguage!");
	}

	@Override
	public CodeAnalyzer createAnalyser(SourceCodeLocation sourceCodeLocation) {
		return new TestLanguageAnalyser(sourceCodeLocation);
	}

	@Override
	public LanguageGrammar getGrammar() {
		return TestLanguageGrammar.getInstance();
	}

	@Override
	public SLOCType getType(AbstractTerminal token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cascadingNode(UniversalSyntaxTree node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int increasesCyclomaticComplexityBy(AbstractProduction production) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HalsteadSymbol getHalsteadResult(AbstractTerminal node) {
		// TODO Auto-generated method stub
		return null;
	}
}
