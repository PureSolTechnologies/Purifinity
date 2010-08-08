package com.puresol.coding.lang.fortran.source.symbols;

import com.puresol.coding.tokentypes.Operant;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenStream;

/**
 * The line lead is the part of six characters at the beginning of each line in
 * Fortran's fixed form representation. This token is not needed for analysis,
 * but it's not allowed to be hidden, otherwise it is treaded as comment and
 * disturbs metrics.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LineLead extends Operant {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	setPatternString("([C*!].....| [ \\d!]{4}[ \\d!;])");
    }

    @Override
    public boolean countForHalstead(Token token, TokenStream tokenStream) {
	return false;
    }

}
