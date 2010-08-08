package com.puresol.coding.lang.fortran.source.symbols;

import com.puresol.coding.lang.fortran.LexicalStructure;
import com.puresol.coding.tokentypes.Operator;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenStream;

public class LParen extends Operator {

    @Override
    protected void initialize() {
	super.initialize();
	setCaseInsensitive();
	setPatternString(LexicalStructure.LEFT_PARENTHESIS);
    }

    @Override
    public String getHalsteadSymbol() {
	return "()";
    }

    @Override
    public boolean countForHalstead(Token token, TokenStream tokenStream) {
	// TODO
	return true;
    }

}
