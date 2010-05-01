package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.tokentypes.Comment;

public class TraditionalComment extends Comment {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString("/\\*([^*]+|\\*[^/])*\\*/");
	}

}
