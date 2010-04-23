package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.java.source.keywords.ThrowsKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ThrowsDeclaration extends AbstractSourceCodeParser {

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (!acceptToken(ThrowsKeyword.class)) {
	    return;
	}
	expectToken(IdLiteral.class);
	while (acceptToken(Comma.class)) {
	    expectToken(IdLiteral.class);
	}
    }

}
