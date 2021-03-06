/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.server.plugin.c11;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.misc.StopWatch;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.parser.ParseTreeNode;
import com.puresoltechnologies.parsers.parser.ParserException;
import com.puresoltechnologies.parsers.parser.packrat.PackratParser;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.parsers.ust.CompilationUnit;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.spi.AbstractCodeAnalyzer;
import com.puresoltechnologies.purifinity.server.plugin.c11.grammar.C11Grammar;
import com.puresoltechnologies.purifinity.server.plugin.c11.ust.TranslationUnitCreator;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class C11Analyzer extends AbstractCodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(C11Analyzer.class);

	private CodeAnalysis fileAnalysis;

	public C11Analyzer(SourceCode sourceCode, HashId hashId) {
		super(sourceCode, hashId, C11Grammar.getInstance());
	}

	@Override
	public void analyze() throws IOException {
		fileAnalysis = null;
		Date date = new Date();
		StopWatch watch = new StopWatch();
		watch.start();
		SourceCode sourceCode = getSourceCode();
		try {
			PackratParser packratParser = new PackratParser(getGrammar());
			ParseTreeNode parserTree = packratParser.parse(sourceCode);
			watch.stop();
			CompilationUnit compilationUnit = TranslationUnitCreator
					.create(parserTree);
			long timeEffort = watch.getMilliseconds();
			AnalysisInformation analyzedFile = new AnalysisInformation(
					getHashId(), date, timeEffort, true, C11.NAME, C11.VERSION,
					C11.ID, C11.PLUGIN_VERSION);
			fileAnalysis = new CodeAnalysis(date, timeEffort, C11.NAME,
					C11.VERSION, analyzedFile,
					this.getAnalyzableCodeRanges(parserTree), compilationUnit);
		} catch (ParserException e) {
			watch.stop();
			long timeEffort = watch.getMilliseconds();
			AnalysisInformation analyzedFile = new AnalysisInformation(
					getHashId(), date, timeEffort, false, C11.NAME,
					C11.VERSION, C11.ID, C11.PLUGIN_VERSION, e.getMessage());
			fileAnalysis = new CodeAnalysis(date, timeEffort, C11.NAME,
					C11.VERSION, analyzedFile, null, null);
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

	private List<CodeRange> getAnalyzableCodeRanges(ParseTreeNode parserTree) {
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

}
