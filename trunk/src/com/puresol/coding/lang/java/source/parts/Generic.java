package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class Generic extends AbstractSourceCodeParser {

	public Generic(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		skipNested(LessThan.class, GreaterThan.class);
	}

}
