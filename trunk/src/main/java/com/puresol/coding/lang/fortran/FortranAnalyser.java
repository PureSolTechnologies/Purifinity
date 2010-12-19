/***************************************************************************
 *
 *   FortranAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.fortran;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;
import com.puresol.utils.StopWatch;

public class FortranAnalyser implements Analyzer {

	private static final long serialVersionUID = 2265150343844799735L;

	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	private final File file;
	private final transient FortranGrammar grammar;
	private Date date = new Date();
	private ParserTree parserTree = null;
	private List<CodeRange> codeRanges = new ArrayList<CodeRange>();

	public FortranAnalyser(File file) {
		super();
		this.file = file;
		grammar = FortranGrammar.getInstance();
	}

	@Override
	public void parse() throws AnalyzerException {
		try {
			TokenStream tokenStream;
			FixedFormFile fixedFormFile = new FixedFormFile(file);
			if (fixedFormFile.isValid()) {
				logger.debug(fixedFormFile.toString());
				tokenStream = scanFixedForm(fixedFormFile);
			} else {
				logger.error(fixedFormFile.toString());
				tokenStream = scanFreeForm();
				// TODO remove throwing AnalyzerException!
				throw new AnalyzerException(this);
			}
			StopWatch watch = new StopWatch();
			Parser parser = grammar.getParser();
			logger.debug("Starting parser...");
			watch.start();
			parserTree = parser.parse(tokenStream);
			watch.stop();
			logger.debug("done. (time: " + watch + ")");
			codeRanges = getLanguage().getAnalyzableCodeRanges(parserTree);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		}
	}

	private TokenStream scanFixedForm(FixedFormFile fixedFormFile)
			throws AnalyzerException {
		try {
			StopWatch watch = new StopWatch();
			logger.debug("Start lexical scanner...");
			watch.start();
			Lexer lexer = grammar.getLexer();
			TokenStream tokenStream = fixedFormFile.scan(lexer);
			watch.stop();
			logger.debug("done. (time: " + watch + ")");
			return tokenStream;
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
	}

	private TokenStream scanFreeForm() throws AnalyzerException {
		try {
			StopWatch watch = new StopWatch();
			logger.debug("Start lexical scanner...");
			watch.start();
			Lexer lexer = grammar.getLexer();
			TokenStream tokenStream = lexer.lex(new FileReader(file),
					file.toString());
			watch.stop();
			logger.debug("done. (time: " + watch + ")");
			return tokenStream;
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
	}

	public Fortran getLanguage() {
		return Fortran.getInstance();
	}

	@Override
	public ParserTree getParserTree() {
		return parserTree;
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public Date getTimeStamp() {
		return date;
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
	public List<CodeRange> getAnalyzableCodeRanges() {
		return codeRanges;
	}

}
