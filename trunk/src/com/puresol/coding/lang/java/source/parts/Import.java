package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.coding.lang.java.source.keywords.ImportKeyword;
import com.puresol.coding.lang.java.source.literals.IdLiteral;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Import extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(ImportKeyword.class);
	expectToken(IdLiteral.class);
	while (isToken(Dot.class)) {
	    expectToken(Dot.class);
	    if (isToken(Star.class)) {
		expectToken(Star.class);
		break;
	    }
	    expectToken(IdLiteral.class);
	}
	expectToken(Semicolon.class);
    }
}
