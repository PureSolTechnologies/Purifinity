package com.puresol.coding.fortran;

import com.puresol.coding.AbstractSourceCodePart;
import com.puresol.coding.SourceCodeParser;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranFile extends AbstractSourceCodePart {

    public FortranFile(SourceCodeParser parser, TokenStream tokenStream,
	    int startPos) {
	super(parser, tokenStream, startPos);
    }

    @Override
    public void scan() throws PartDoesNotMatchException {
	// TODO to be implemented!
    }

}
