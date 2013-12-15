/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresoltechnologies.purifinity.coding.lang.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresoltechnologies.commons.utils.StopWatch;
import com.puresoltechnologies.parsers.api.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.parsers.api.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.lexer.LexerException;
import com.puresoltechnologies.parsers.impl.lexer.TokenStream;
import com.puresoltechnologies.parsers.impl.parser.Parser;
import com.puresoltechnologies.parsers.impl.parser.ParserException;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;
import com.puresoltechnologies.parsers.impl.ust.CompilationUnit;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzerException;
import com.puresoltechnologies.purifinity.analysis.api.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.api.CodeRange;
import com.puresoltechnologies.purifinity.analysis.api.CodeRangeType;
import com.puresoltechnologies.purifinity.analysis.api.ProgrammingLanguage;
import com.puresoltechnologies.purifinity.coding.analysis.impl.AbstractCodeAnalyzer;
import com.puresoltechnologies.purifinity.coding.lang.test.grammar.TestLanguageGrammar;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguageAnalyser extends AbstractCodeAnalyzer {

	private static final Logger logger = LoggerFactory
			.getLogger(TestLanguageAnalyser.class);

	private CodeAnalysis fileAnalysis;

	public TestLanguageAnalyser(SourceCodeLocation sourceCodeLocation) {
		super(sourceCodeLocation, TestLanguageGrammar.getInstance());
	}

	@Override
	public void analyze() throws AnalyzerException {
		try {
			fileAnalysis = null;
			Date date = new Date();
			StopWatch watch = new StopWatch();
			watch.start();
			SourceCode sourceCode = getSource().loadSourceCode();
			Lexer lexer = getGrammar().getLexer();
			TokenStream tokenStream = lexer.lex(sourceCode);
			Parser parser = getGrammar().getParser();
			ParserTree parserTree = parser.parse(tokenStream);
			watch.stop();
			CompilationUnit compilationUnit = null; // TODO
			long timeEffort = Math.round(watch.getSeconds() * 1000.0);
			TestLanguage language = TestLanguage.getInstance();
			fileAnalysis = new CodeAnalysis(date, timeEffort,
					language.getName(), language.getVersion(),
					new AnalyzedCode(sourceCode.getHashId(), getSource(), date,
							timeEffort, language.getName(), language
									.getVersion()),
					getAnalyzableCodeRanges(compilationUnit), compilationUnit);

		} catch (LexerException | ParserException | IOException e) {
			throw new AnalyzerException(this, e);
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

	private List<CodeRange> getAnalyzableCodeRanges(UniversalSyntaxTree ust) {
		List<CodeRange> result = new ArrayList<CodeRange>();
		result.add(new CodeRange("", "", CodeRangeType.FILE, ust));
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
	public CodeAnalysis getAnalysis() {
		return fileAnalysis;
	}

}
