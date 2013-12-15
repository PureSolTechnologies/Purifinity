package com.puresoltechnologies.purifinity.coding.store.fs.analysis;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalyzer;
import com.puresoltechnologies.purifinity.analysis.api.LanguageNotSupportedException;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.coding.analysis.impl.CodeAnalyzerFactory;

public class CodeAnalyzerImpl implements CodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(CodeAnalyzerImpl.class);

	private final SourceCodeLocation source;
	private CodeAnalyzer analyzer = null;
	private boolean analyzed = false;
	private long timeOfRun;

	public CodeAnalyzerImpl(SourceCodeLocation source, HashId hashId)
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
	public SourceCodeLocation getSource() {
		return source;
	}

}
