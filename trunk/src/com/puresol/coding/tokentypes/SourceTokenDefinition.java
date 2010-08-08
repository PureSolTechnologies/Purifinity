package com.puresol.coding.tokentypes;

import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenStream;

public interface SourceTokenDefinition {

    public abstract int changeBlockLayer();

    public abstract SymbolType getSymbolType();

    public abstract boolean isPrimitiveDataType();

    public abstract int getCyclomaticNumber(Token token,
	    TokenStream tokenStream);

    public abstract boolean countForHalstead(Token token,
	    TokenStream tokenStream);

    public abstract String getHalsteadSymbol();
}
