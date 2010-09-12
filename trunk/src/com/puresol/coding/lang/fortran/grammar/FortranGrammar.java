package com.puresol.coding.lang.fortran.grammar;

import java.io.File;
import java.io.IOException;

import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarManager;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerFactoryException;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserFactoryException;

public class FortranGrammar extends GrammarManager {

	private static final String RESOURCE = "src/com/puresol/coding/lang/fortran/grammar/Fortran2008.g";

	private static FortranGrammar instance = null;

	public static FortranGrammar getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new FortranGrammar();
		}
	}

	public static Lexer createLexer() throws IOException, GrammarException,
			LexerFactoryException {
		return getInstance().getLexer();
	}

	public static Parser createParser() throws IOException, GrammarException,
			ParserFactoryException {
		return getInstance().getParser();
	}

	private FortranGrammar() {
		super(new File(RESOURCE));
	}
}
