package com.puresol.html.css.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.puresol.html.css.parser.tokengroups.CSSKeywords;
import com.puresol.html.css.parser.tokengroups.CSSLiterals;
import com.puresol.html.css.parser.tokengroups.CSSSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.LexerException;
import com.puresol.parser.TokenStream;

public class CSSLexer extends Lexer {

    public CSSLexer(TokenStream stream) throws LexerException {
	super(stream);
	addDefinitions(CSSKeywords.class);
	addDefinitions(CSSLiterals.class);
	addDefinitions(CSSSymbols.class);
    }

    public CSSLexer(File file) throws FileNotFoundException, IOException,
	    LexerException {
	super(file);
	addDefinitions(CSSKeywords.class);
	addDefinitions(CSSLiterals.class);
	addDefinitions(CSSSymbols.class);
    }

}
