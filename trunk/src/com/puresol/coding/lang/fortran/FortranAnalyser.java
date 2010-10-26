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

import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;

public class FortranAnalyser {

	private static final long serialVersionUID = 2265150343844799735L;

	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	private final File file;

	public FortranAnalyser(File file) {
		super();
		this.file = file;
	}

	public void parse() throws FortranException {
		try {
			Lexer lexer = FortranGrammar.createLexer();
			TokenStream tokenStream = lexer.lex(new FileReader(file));
			Parser parser = FortranGrammar.createParser();
			AST ast = parser.parse(tokenStream);
			ast.getChildren();
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw new FortranException(e.getMessage(), e);
		}
		return;
	}

	public Fortran getLanguage() {
		return Fortran.getInstance();
	}
}
