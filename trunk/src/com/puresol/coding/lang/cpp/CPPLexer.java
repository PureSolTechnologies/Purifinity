package com.puresol.coding.lang.cpp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.puresol.coding.lang.cpp.source.tokengroups.CPPKeywords;
import com.puresol.coding.lang.cpp.source.tokengroups.CPPLiterals;
import com.puresol.coding.lang.cpp.source.tokengroups.CPPSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.LexerException;
import com.puresol.parser.TokenStream;

public class CPPLexer extends Lexer {

    public CPPLexer(TokenStream stream) throws LexerException {
	super(stream);
	init();
    }

    public CPPLexer(File directory, File file) throws FileNotFoundException,
	    IOException, LexerException {
	super(directory, file);
	init();
    }

    private void init() throws LexerException {
	addDefinitions(CPPKeywords.DEFINITIONS);
	addDefinitions(CPPLiterals.DEFINITIONS);
	addDefinitions(CPPSymbols.DEFINITIONS);
    }
}
