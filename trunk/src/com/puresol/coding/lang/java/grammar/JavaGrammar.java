package com.puresol.coding.lang.java.grammar;

import java.io.File;
import java.io.IOException;

import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarManager;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerFactoryException;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserFactoryException;

public class JavaGrammar extends GrammarManager {

	private static final String RESOURCE = "src/com/puresol/coding/lang/java/grammar/Java-1.6.g";

	private static JavaGrammar instance = null;

	public static JavaGrammar getInstance() {
		if (instance == null) {
			createInstance();
		}
		return instance;
	}

	private static synchronized void createInstance() {
		if (instance == null) {
			instance = new JavaGrammar();
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

	private JavaGrammar() {
		super(new File(RESOURCE));
	}
}
