package com.puresol.coding.fortran;

import javax.i18n4j.Translator;

import com.puresol.coding.SourceCodeParser;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.parser.Part;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class FortranParser extends SourceCodeParser {

    private static final Translator translator =
	    Translator.getTranslator(FortranParser.class);

    public FortranParser(TokenStream tokenStream) {
	super(tokenStream);
    }

    public void parser(Class<? extends Part> part)
	    throws PartDoesNotMatchException {
	CodeRange codeRange =
		new CodeRange(getTokenStream().getName(),
			CodeRangeType.FILE, translator.i18n("File"),
			getTokenStream(), 0,
			getTokenStream().getSize() - 1);
	addCodeRange(codeRange);
	super.parse(part);
    }
}
