package com.puresol.coding.lang.fortran;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranParser extends AbstractSourceCodeParser {

	public FortranParser(TokenStream tokenStream) {
		super(tokenStream, 0);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		// TODO Auto-generated method stub
	}
}
