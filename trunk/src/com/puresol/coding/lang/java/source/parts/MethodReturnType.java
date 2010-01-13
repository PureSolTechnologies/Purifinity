package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class MethodReturnType extends AbstractSourceCodeParser {

	public MethodReturnType(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		if (processTokenIfPossible(VoidKeyword.class)) {
			return;
		}
		processPart(VariableType.class);
	}

}
