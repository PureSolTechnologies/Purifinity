package com.puresol.html.css.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.puresol.html.css.parser.tokengroups.CSSKeywords;
import com.puresol.html.css.parser.tokengroups.CSSLiterals;
import com.puresol.html.css.parser.tokengroups.CSSSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.TokenStream;

public class CSSLexer extends Lexer {

	public CSSLexer(TokenStream stream) {
		super(stream);
	}

	public CSSLexer(File file) throws FileNotFoundException, IOException {
		super(file);
		addDefinitions(CSSKeywords.class);
		addDefinitions(CSSLiterals.class);
		addDefinitions(CSSSymbols.class);

	}

}
