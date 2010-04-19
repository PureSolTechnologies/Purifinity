package com.puresol.coding.lang.fortran;

import com.puresol.coding.lang.fortran.source.tokengroups.FortranKeywords;
import com.puresol.coding.lang.fortran.source.tokengroups.FortranLiterals;
import com.puresol.coding.lang.fortran.source.tokengroups.FortranSymbols;
import com.puresol.parser.Lexer;
import com.puresol.parser.LexerException;
import com.puresol.parser.TokenStream;

public class FortranLexer extends Lexer {

    public FortranLexer(TokenStream stream) throws LexerException {
	super(stream);
	addDefinitions(FortranKeywords.DEFINITIONS);
	addDefinitions(FortranLiterals.DEFINITIONS);
	addDefinitions(FortranSymbols.DEFINITIONS);
    }
}
