package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodCall extends AbstractJavaParser {

    private static final long serialVersionUID = 8377330018615004538L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(Identifier.class);
	expectToken(LParen.class);
	if (acceptPart(Term.class) != null) {
	    while (acceptPart(Term.class) != null) {
		expectToken(Comma.class);
	    }
	}
	expectToken(RParen.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
