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
import java.util.Date;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerFactoryException;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserFactoryException;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;
import com.puresol.utils.StopWatch;

public class FortranAnalyser implements Analyzer {

	private static final long serialVersionUID = 2265150343844799735L;

	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	private final File file;
	private Date date = new Date();
	private AST ast = null;

	public FortranAnalyser(File file) {
		super();
		this.file = file;
	}

	public void parse() throws AnalyzerException {
		try {
			StopWatch watch = new StopWatch();
			logger.debug("Start lexical scanner...");
			watch.start();
			Lexer lexer = FortranGrammar.createLexer();
			TokenStream tokenStream = lexer.lex(new FileReader(file),
					file.toString());
			watch.stop();
			logger.debug("done. (time: " + watch + ")");
			Parser parser = FortranGrammar.createParser();
			logger.debug("Starting parser...");
			watch.start();
			AST ast = parser.parse(tokenStream);
			watch.stop();
			logger.debug("done. (time: " + watch + ")");
			ast.getChildren();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (GrammarException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (LexerFactoryException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (ParserFactoryException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		}
		return;
	}

	public Fortran getLanguage() {
		return Fortran.getInstance();
	}

	@Override
	public AST getAST() {
		return ast;
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
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}
}
