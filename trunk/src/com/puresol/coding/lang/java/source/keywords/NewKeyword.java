package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.Operator;

public class NewKeyword extends Operator {

    @Override
    public String getPatternString() {
	return "new";
    }

}
