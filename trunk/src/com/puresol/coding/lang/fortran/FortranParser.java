package com.puresol.coding.lang.fortran;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.Language;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranParser extends AbstractSourceCodeParser {

    public FortranParser(TokenStream tokenStream) {
	setTokenStream(tokenStream);
	setStartPosition(0);
    }

    @Override
    public void scan() throws PartDoesNotMatchException {
	TokenStream tokenStream = getTokenStream();
	addCodeRange(Language.FORTRAN, CodeRangeType.FILE, 0, tokenStream
		.getSize() - 1);

	try {
	    moveForward(0);
	} catch (EndOfTokenStreamException e) {
	    // this may happen if there is an empty file...
	    return;
	}

	// TODO build something in here...
    }
}
