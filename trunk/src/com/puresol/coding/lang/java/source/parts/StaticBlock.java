package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class StaticBlock extends AbstractSourceCodeParser {

	public StaticBlock(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		processToken(StaticKeyword.class);
		processPart(CodeBlock.class);
	}

}
