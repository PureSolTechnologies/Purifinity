package com.puresol.coding.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.puresol.coding.ProgrammingLanguage;
import com.puresol.parser.lexer.Lexer;
import com.puresol.parser.lexer.LexerException;
import com.puresol.parser.tokens.TokenStream;

public class SourceCodeLexer extends Lexer {

	public SourceCodeLexer(ProgrammingLanguage language, TokenStream stream)
			throws LexerException {
		super(stream);
	}

	public SourceCodeLexer(ProgrammingLanguage language, File file)
			throws LexerException, FileNotFoundException, IOException {
		super(file);
	}

	public SourceCodeLexer(ProgrammingLanguage language, String fileName,
			String text) throws LexerException {
		super(fileName, text);
	}
}
