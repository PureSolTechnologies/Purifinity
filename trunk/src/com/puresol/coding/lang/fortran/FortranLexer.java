package com.puresol.coding.lang.fortran;

import com.puresol.coding.lang.fortran.source.tokengroups.FortranKeywords;
import com.puresol.coding.lang.fortran.source.tokengroups.FortranLiterals;
import com.puresol.coding.lang.fortran.source.tokengroups.FortranSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.TokenStream;

public class FortranLexer extends Lexer {

	public FortranLexer(TokenStream stream) {
		super(stream);
		addDefinitions(FortranKeywords.class);
		addDefinitions(FortranLiterals.class);
		addDefinitions(FortranSymbols.class);
	}

}
