package com.puresol.coding.lang.cpp;

import com.puresol.coding.lang.cpp.source.tokengroups.CPPKeywords;
import com.puresol.coding.lang.cpp.source.tokengroups.CPPLiterals;
import com.puresol.coding.lang.cpp.source.tokengroups.CPPSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.TokenStream;

public class CPPLexer extends Lexer {

    public CPPLexer(TokenStream stream) {
	super(stream);
	addDefinitions(CPPKeywords.class);
	addDefinitions(CPPLiterals.class);
	addDefinitions(CPPSymbols.class);
    }

}
