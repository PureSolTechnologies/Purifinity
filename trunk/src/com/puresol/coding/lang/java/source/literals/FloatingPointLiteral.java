package com.puresol.coding.lang.java.source.literals;

import com.puresol.coding.tokentypes.Literal;

public class FloatingPointLiteral extends Literal {

    @Override
    public String getPatternString() {
	return "(\\+|-)?\\d*\\.\\d+((e|E)(\\+|-)?\\d+)?(?!\\w)";
    }

}
