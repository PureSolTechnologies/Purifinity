package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class IntegerKeyword extends PrimitiveDataType {

    @Override
    public String getPatternString() {
	setCaseInsensitive();
	return "INTEGER";
    }

}
