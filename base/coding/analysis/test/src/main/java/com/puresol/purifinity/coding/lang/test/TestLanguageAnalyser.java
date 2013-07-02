/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.purifinity.coding.lang.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.commons.utils.StopWatch;
import com.puresol.purifinity.coding.analysis.api.AnalyzedCode;
import com.puresol.purifinity.coding.analysis.api.AnalyzerException;
import com.puresol.purifinity.coding.analysis.api.CodeAnalysis;
import com.puresol.purifinity.coding.analysis.api.CodeAnalyzer;
import com.puresol.purifinity.coding.analysis.api.CodeRange;
import com.puresol.purifinity.coding.analysis.api.CodeRangeType;
import com.puresol.purifinity.coding.lang.api.ProgrammingLanguage;
import com.puresol.purifinity.coding.lang.test.grammar.TestLanguageGrammar;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.lexer.LexerException;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.parser.Parser;
import com.puresol.purifinity.uhura.parser.ParserException;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.uhura.source.SourceCode;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguageAnalyser implements CodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(TestLanguageAnalyser.class);

	private final CodeLocation sourceCodeLocation;
	private final transient TestLanguageGrammar grammar;
	private CodeAnalysis fileAnalysis;

	public TestLanguageAnalyser(CodeLocation sourceCodeLocation) {
		super();
		this.sourceCodeLocation = sourceCodeLocation;
		grammar = TestLanguageGrammar.getInstance();
	}

	@Override
	public void analyze() throws AnalyzerException {
		try {
			fileAnalysis = null;
			Date date = new Date();
			StopWatch watch = new StopWatch();
			watch.start();
			SourceCode sourceCode = sourceCodeLocation.loadSourceCode();
			Lexer lexer = grammar.getLexer();
			TokenStream tokenStream = lexer.lex(sourceCode);
			Parser parser = grammar.getParser();
			ParserTree parserTree = parser.parse(tokenStream);
			watch.stop();
			long timeEffort = Math.round(watch.getSeconds() * 1000.0);
			TestLanguage language = TestLanguage.getInstance();
			fileAnalysis = new CodeAnalysis(date, timeEffort,
					language.getName(), language.getVersion(),
					new AnalyzedCode(sourceCode.getHashId(),
							sourceCodeLocation, date, timeEffort, language
									.getName(), language.getVersion()),
					parserTree, getAnalyzableCodeRanges(parserTree));

		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		}
		return;
	}

	@Override
	public ProgrammingLanguage getLanguage() {
		return TestLanguage.getInstance();
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
		List<CodeRange> result = new ArrayList<CodeRange>();
		result.add(new CodeRange("", "", CodeRangeType.FILE, parserTree));
		return result;
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
	public CodeLocation getSource() {
		return sourceCodeLocation;
	}

	@Override
	public CodeAnalysis getAnalysis() {
		return fileAnalysis;
	}

}
