package com.puresol.reporting.html.css.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.puresol.parser.Lexer;
import com.puresol.parser.LexerException;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.html.css.parser.tokengroups.CSSKeywords;
import com.puresol.reporting.html.css.parser.tokengroups.CSSLiterals;
import com.puresol.reporting.html.css.parser.tokengroups.CSSSymbols;

public class CSSLexer extends Lexer {

	public CSSLexer(TokenStream stream) throws LexerException {
		super(stream);
		addDefinitions(CSSKeywords.class);
		addDefinitions(CSSLiterals.class);
		addDefinitions(CSSSymbols.class);
	}

	public CSSLexer(File directory, File file) throws FileNotFoundException,
			IOException, LexerException {
		super(directory, file);
		addDefinitions(CSSKeywords.class);
		addDefinitions(CSSLiterals.class);
		addDefinitions(CSSSymbols.class);
	}

}
