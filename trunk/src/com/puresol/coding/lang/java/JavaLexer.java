package com.puresol.coding.lang.java;

import com.puresol.coding.lang.java.source.tokengroups.JavaKeywords;
import com.puresol.coding.lang.java.source.tokengroups.JavaLiterals;
import com.puresol.coding.lang.java.source.tokengroups.JavaSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.LexerException;
import com.puresol.parser.TokenStream;

public class JavaLexer extends Lexer {

    public JavaLexer(TokenStream stream) throws LexerException {
	super(stream);
	addDefinitions(JavaKeywords.DEFINITIONS);
	addDefinitions(JavaLiterals.DEFINITIONS);
	addDefinitions(JavaSymbols.DEFINITIONS);
    }

}
