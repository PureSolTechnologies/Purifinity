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

import org.apache.log4j.Logger;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.coding.lang.java.grammar.JavaGrammar;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;

/**
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaAnalyser {

	private static final long serialVersionUID = -3601131473616977648L;

	private static final Logger logger = Logger.getLogger(JavaAnalyser.class);

	private final File file;

	public JavaAnalyser(File file) {
		super();
		this.file = file;
	}

	public void parse() throws JavaException {
		try {
			Lexer lexer = JavaGrammar.createLexer();
			TokenStream tokenStream = lexer.lex(new FileReader(file));
			Parser parser = JavaGrammar.createParser();
			AST ast = parser.parse(tokenStream);
			ast.getChildren();
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw new JavaException(e.getMessage(), e);
		}
		return;
	}

	public ProgrammingLanguage getLanguage() {
		return Java.getInstance();
	}

}
