package com.puresol.coding.java;

import com.puresol.coding.java.tokengroups.JavaKeywords;
import com.puresol.coding.java.tokengroups.JavaLiterals;
import com.puresol.coding.java.tokengroups.JavaSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.TokenStream;

public class JavaLexer extends Lexer {

    public JavaLexer(TokenStream stream) {
	super(stream);
	addDefinitions(JavaKeywords.class);
	addDefinitions(JavaLiterals.class);
	addDefinitions(JavaSymbols.class);
    }

}
