package com.puresol.coding.lang.java.source.symbols;

import com.puresol.coding.lang.java.LexicalStructure;
import com.puresol.coding.tokentypes.Comment;

/**
 * This is a class representing Java's end of line comment started with '//'.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EndOfLineComment extends Comment {

	@Override
	protected void initialize() {
		super.initialize();
		setPatternString(LexicalStructure.END_OF_LINE_COMMENT);
	}

}
