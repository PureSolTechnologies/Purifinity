/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.lang.test.grammar.TestLanguageGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TestLanguageAnalyser implements Analyzer {

	private static final long serialVersionUID = -3601131473616977648L;

	private static final Logger logger = Logger
			.getLogger(TestLanguageAnalyser.class);

	private final File file;
	private final transient TestLanguageGrammar grammar;
	private Date date = new Date();
	private ParserTree parserTree = null;

	public TestLanguageAnalyser(File file) {
		super();
		this.file = file;
		grammar = TestLanguageGrammar.getInstance();
	}

	@Override
	public void parse() throws AnalyzerException {
		try {
			date = new Date();
			Lexer lexer = grammar.getLexer();
			TokenStream tokenStream = lexer.lex(new FileReader(file),
					file.toString());
			Parser parser = grammar.getParser();
			parserTree = parser.parse(tokenStream);
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (PersistenceException e) {
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
	public Date getTimeStamp() {
		return date;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public boolean persist(File file) {
		try {
			Persistence.persist(this, file);
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public ParserTree getParserTree() {
		return parserTree;
	}

	@Override
	public List<CodeRange> getAnalyzableCodeRanges() {
		List<CodeRange> result = new ArrayList<CodeRange>();
		result.add(new CodeRange("", CodeRangeType.FILE, parserTree));
		return result;
	}
}
