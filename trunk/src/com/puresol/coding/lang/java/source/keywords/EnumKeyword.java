package com.puresol.coding.lang.java.source.keywords;

import com.puresol.coding.tokentypes.Operator;

public class EnumKeyword extends Operator {

	@Override
	public String getPatternString() {
		return "enum";
	}

}
