package com.puresol.coding.java.tokens;

import com.puresol.coding.tokentypes.Operator;

public class QuestionMark extends Operator {

	@Override
	public String getPatternString() {
		return "\\?";
	}

}
