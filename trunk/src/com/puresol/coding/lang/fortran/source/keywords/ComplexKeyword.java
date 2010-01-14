package com.puresol.coding.lang.fortran.source.keywords;

import com.puresol.coding.tokentypes.PrimitiveDataType;

public class ComplexKeyword extends PrimitiveDataType {

    @Override
    public String getPatternString() {
	setCaseInsensitive();
	return "COMPLEX";
    }

}
