package com.puresol.reporting.html.css.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.puresol.parser.lexer.Lexer;
import com.puresol.parser.lexer.LexerException;
import com.puresol.parser.tokens.TokenStream;
import com.puresol.reporting.html.css.parser.tokengroups.CSSKeywords;
import com.puresol.reporting.html.css.parser.tokengroups.CSSLiterals;
import com.puresol.reporting.html.css.parser.tokengroups.CSSSymbols;

public class CSSLexer extends Lexer {

	public CSSLexer(TokenStream stream) throws LexerException {
		super(stream);
		addDefinitions(CSSKeywords.DEFINITIONS);
		addDefinitions(CSSLiterals.DEFINITIONS);
		addDefinitions(CSSSymbols.DEFINITIONS);
	}

	public CSSLexer(File file) throws FileNotFoundException, IOException,
			LexerException {
		super(file);
		addDefinitions(CSSKeywords.DEFINITIONS);
		addDefinitions(CSSLiterals.DEFINITIONS);
		addDefinitions(CSSSymbols.DEFINITIONS);
	}

}
