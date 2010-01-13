package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class CodeBlock extends AbstractSourceCodeParser {

	public CodeBlock(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		skipNested(LCurlyBracket.class, RCurlyBracket.class);
	}

}
