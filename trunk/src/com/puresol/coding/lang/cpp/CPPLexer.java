package com.puresol.coding.lang.cpp;

import com.puresol.coding.lang.java.source.tokengroups.JavaKeywords;
import com.puresol.coding.lang.java.source.tokengroups.JavaLiterals;
import com.puresol.coding.lang.java.source.tokengroups.JavaSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.TokenStream;

public class CPPLexer extends Lexer {

    public CPPLexer(TokenStream stream) {
	super(stream);
	addDefinitions(JavaKeywords.class);
	addDefinitions(JavaLiterals.class);
	addDefinitions(JavaSymbols.class);
    }

}
