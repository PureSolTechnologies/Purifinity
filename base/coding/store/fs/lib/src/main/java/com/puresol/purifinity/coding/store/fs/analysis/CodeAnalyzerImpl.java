package com.puresol.purifinity.coding.store.fs.analysis;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.purifinity.coding.analysis.api.AnalyzerException;
import com.puresol.purifinity.coding.analysis.api.CodeAnalysis;
import com.puresol.purifinity.coding.analysis.api.CodeAnalyzer;
import com.puresol.purifinity.coding.analysis.api.CodeAnalyzerFactory;
import com.puresol.purifinity.coding.analysis.api.LanguageNotSupportedException;
import com.puresol.purifinity.coding.lang.api.ProgrammingLanguage;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.utils.HashId;

public class CodeAnalyzerImpl implements CodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(CodeAnalyzerImpl.class);

	private final CodeLocation source;
	private CodeAnalyzer analyzer = null;
	private boolean analyzed = false;
	private long timeOfRun;

	public CodeAnalyzerImpl(CodeLocation source, HashId hashId)
			throws AnalyzerException {
		super();
		this.source = source;
	}

	@Override
	public void analyze() throws AnalyzerException, IOException {
		try {
			analyzed = false;
			timeOfRun = System.currentTimeMillis();
			analyzeFile();
			analyzed = true;
			timeOfRun = System.currentTimeMillis() - timeOfRun;
		} catch (LanguageNotSupportedException e) {
			logger.debug("File '" + source.getHumanReadableLocationString()
					+ "' could not be analyzed due to contents in a "
					+ "non-supported language.");
		}
	}

	private void analyzeFile() throws IOException, AnalyzerException,
			LanguageNotSupportedException {
		analyzer = CodeAnalyzerFactory.createFactory().create(source);
		analyzer.analyze();
	}

	public boolean isAnalyzed() {
		return analyzed;
	}

	public CodeAnalyzer getAnalyzer() {
		return analyzer;
	}

	@Override
	public CodeAnalysis getAnalysis() {
		return analyzer.getAnalysis();
	}

	@Override
	public ProgrammingLanguage getLanguage() {
		return analyzer.getLanguage();
	}

	@Override
	public boolean persist(File file) {
		return false;
	}

	@Override
	public CodeLocation getSource() {
		return source;
	}

}