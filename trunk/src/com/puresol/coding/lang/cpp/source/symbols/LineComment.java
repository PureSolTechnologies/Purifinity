package com.puresol.coding.lang.cpp.source.symbols;

import com.puresol.coding.tokentypes.Comment;

public class LineComment extends Comment {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("//[^\\n]*");
	}

}
