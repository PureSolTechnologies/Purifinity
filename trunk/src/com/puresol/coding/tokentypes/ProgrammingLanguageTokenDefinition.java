package com.puresol.coding.tokentypes;

import com.puresol.parser.Token;
import com.puresol.parser.TokenPublicity;
import com.puresol.parser.TokenStream;

public interface ProgrammingLanguageTokenDefinition {
	public abstract TokenPublicity getDefaultPublicity();

	public abstract int changeBlockLayer();

	public abstract SymbolType getSymbolType();

	public abstract boolean isPrimitiveDataType();

	public abstract int getCyclomaticNumber(Token token, TokenStream tokenStream);

	public abstract boolean countForHalstead();

	public abstract String getHalsteadSymbol();
}
