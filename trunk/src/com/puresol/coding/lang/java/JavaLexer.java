package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.puresol.coding.lang.java.source.tokengroups.JavaKeywords;
import com.puresol.coding.lang.java.source.tokengroups.JavaLiterals;
import com.puresol.coding.lang.java.source.tokengroups.JavaSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.LexerException;
import com.puresol.parser.TokenStream;

public class JavaLexer extends Lexer {

    public JavaLexer(TokenStream stream) throws LexerException {
	super(stream);
	init();
    }

    public JavaLexer(File directory, File file) throws LexerException,
	    FileNotFoundException, IOException {
	super(directory, file);
	init();
    }

    private void init() throws LexerException {
	addDefinitions(JavaKeywords.DEFINITIONS);
	addDefinitions(JavaLiterals.DEFINITIONS);
	addDefinitions(JavaSymbols.DEFINITIONS);
    }
}
