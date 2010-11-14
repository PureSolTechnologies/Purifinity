/***************************************************************************
 *
 *   JavaAnalyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.analysis.AnalyzerException;
import com.puresol.coding.analysis.Analyzer;
import com.puresol.coding.lang.java.grammar.JavaGrammar;
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

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyser implements Analyzer {

	private static final long serialVersionUID = -3601131473616977648L;

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

	private final File file;
	private Date date = new Date();
	private AST ast = null;

	public JavaAnalyser(File file) {
		super();
		this.file = file;
	}

	@Override
	public void parse() throws AnalyzerException {
		try {
			date = new Date();
			Lexer lexer = JavaGrammar.createLexer();
			TokenStream tokenStream = lexer.lex(new FileReader(file));
			Parser parser = JavaGrammar.createParser();
			ast = parser.parse(tokenStream);
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (GrammarException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (LexerFactoryException e) {
			logger.error(e.getMessage(), e);
			throw new AnalyzerException(this);
		} catch (ParserFactoryException e) {
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
		return Java.getInstance();
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
		} catch (PersistenceException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public AST getAST() {
		return ast;
	}

}
