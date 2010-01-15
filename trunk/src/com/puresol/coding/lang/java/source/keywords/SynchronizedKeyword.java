package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class SynchronizedKeyword extends PrimitiveDataType {

    @Override
    public String getPatternString() {
	return "synchronized";
    }

}
