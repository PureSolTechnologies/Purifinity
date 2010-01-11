package com.puresol.coding.tokentypes;

import com.puresol.parser.AbstractTokenDefinition;
import com.puresol.parser.TokenPublicity;

public abstract class AbstractProgrammingLanguageTokenDefinition extends
		AbstractTokenDefinition {

	public abstract TokenPublicity getDefaultPublicity();

	public abstract int changeBlockLayer();

	public abstract SymbolType getSymbolType();

	public abstract boolean isPrimitiveDataType();
}
