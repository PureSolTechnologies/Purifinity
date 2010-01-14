package com.puresol.coding.lang.fortran;

import javax.i18n4j.Translator;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranParser extends AbstractSourceCodeParser {

    private static final Translator translator =
	    Translator.getTranslator(FortranParser.class);

    public FortranParser(TokenStream tokenStream) {
	setTokenStream(tokenStream);
	setStartPosition(0);
    }

    @Override
    public void scan() throws PartDoesNotMatchException {
	TokenStream tokenStream = getTokenStream();
	addCodeRange(new CodeRange(tokenStream.getName(),
		CodeRangeType.FILE, translator.i18n("File"), tokenStream,
		0, tokenStream.getSize() - 1));

	try {
	    moveForward(0);
	} catch (EndOfTokenStreamException e) {
	    // this may happen if there is an empty file...
	    return;
	}

	// TODO build something in here...
    }
}
