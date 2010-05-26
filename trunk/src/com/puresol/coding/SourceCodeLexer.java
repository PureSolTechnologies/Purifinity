package com.puresol.coding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.puresol.parser.Lexer;
import com.puresol.parser.LexerException;
import com.puresol.parser.TokenStream;

public class SourceCodeLexer extends Lexer {

    private final ProgrammingLanguage language;

    public SourceCodeLexer(ProgrammingLanguage language, TokenStream stream)
	    throws LexerException {
	super(stream);
	this.language = language;
	init();
    }

    public SourceCodeLexer(ProgrammingLanguage language, File directory,
	    File file) throws LexerException, FileNotFoundException,
	    IOException {
	super(directory, file);
	this.language = language;
	init();
    }

    public SourceCodeLexer(ProgrammingLanguage language, String fileName,
	    String text) throws LexerException {
	super(fileName, text);
	this.language = language;
	init();
    }

    private void init() throws LexerException {
	addDefinitions(language.getKeywords());
	addDefinitions(language.getLiterals());
	addDefinitions(language.getSymbols());
    }
}
