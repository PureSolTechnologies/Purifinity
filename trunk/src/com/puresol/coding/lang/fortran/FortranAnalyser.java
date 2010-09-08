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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerFactory;
import com.puresol.uhura.lexer.LexerFactoryException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserFactory;
import com.puresol.uhura.parser.ParserFactoryException;
import com.puresol.uhura.parser.lr.LR1Parser;

public class FortranAnalyser {

	private static final long serialVersionUID = 2265150343844799735L;

	private static final Logger logger = Logger
			.getLogger(FortranAnalyser.class);

	private final File file;

	public FortranAnalyser(File file) {
		this.file = file;
	}

	public void parse() {
		try {
			Grammar grammar = FortranGrammar.get();

			Lexer lexer = LexerFactory.create(grammar);
			TokenStream tokenStream = lexer.lex(new FileReader(file));
			Parser parser = ParserFactory.create(grammar);
			AST ast = parser.parse(tokenStream);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LexerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LexerFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public Fortran getLanguage() {
		return Fortran.getInstance();
	}
}
