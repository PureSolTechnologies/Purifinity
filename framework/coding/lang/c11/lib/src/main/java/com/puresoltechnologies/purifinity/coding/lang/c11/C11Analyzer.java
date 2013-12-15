/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.coding.lang.c11;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.utils.StopWatch;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.parsers.impl.parser.ParserException;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;
import com.puresoltechnologies.parsers.impl.parser.packrat.PackratParser;
import com.puresoltechnologies.parsers.impl.ust.CompilationUnit;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.coding.analysis.impl.AbstractCodeAnalyzer;
import com.puresoltechnologies.purifinity.coding.lang.c11.grammar.C11Grammar;
import com.puresoltechnologies.purifinity.coding.lang.c11.ust.TranslationUnitCreator;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class C11Analyzer extends AbstractCodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(C11Analyzer.class);

	private CodeAnalysis fileAnalysis;

	public C11Analyzer(SourceCodeLocation sourceCodeLocation) {
		super(sourceCodeLocation, C11Grammar.getInstance());
	}

	@Override
	public void analyze() throws AnalyzerException {
		try {
			fileAnalysis = null;
			Date date = new Date();
			StopWatch watch = new StopWatch();
			SourceCode sourceCode = getSource().loadSourceCode();
			watch.start();
			PackratParser packratParser = new PackratParser(getGrammar());
			ParserTree parserTree = packratParser.parse(sourceCode);
			watch.stop();
			CompilationUnit compilationUnit = TranslationUnitCreator
					.create(parserTree);
			long timeEffort = Math.round(watch.getSeconds() * 1000.0);
			C11 c11 = C11.getInstance();
			AnalyzedCode analyzedFile = new AnalyzedCode(
					sourceCode.getHashId(), getSource(), date, timeEffort,
					c11.getName(), c11.getVersion());
			fileAnalysis = new CodeAnalysis(date, timeEffort, c11.getName(),
					c11.getVersion(), analyzedFile,
					this.getAnalyzableCodeRanges(parserTree), compilationUnit);
		} catch (ParserException | IOException e) {
			throw new AnalyzerException(this, e);
		}
		return;
	}

	@Override
	public CodeAnalysis getAnalysis() {
		return fileAnalysis;
	}

	@Override
	public boolean persist(File file) {
		try {
			persist(this, file);
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	private List<CodeRange> getAnalyzableCodeRanges(ParserTree parserTree) {
		throw new IllegalStateException("No implemented, yet!");
	}

	private <T> void persist(T object, File file) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(file));
		try {
			objectOutputStream.writeObject(object);
		} finally {
			objectOutputStream.close();
		}
	}

	@Override
	public ProgrammingLanguage getLanguage() {
		return C11.getInstance();
	}

}
