package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Identifier extends AbstractJavaParser {

    private static final long serialVersionUID = 8377330018615004538L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptToken(SuperKeyword.class) != null) {
	    expectToken(Dot.class);
	} else if (acceptToken(ThisKeyword.class) != null) {
	    expectToken(Dot.class);
	}
	if (acceptPart(MethodCall.class) == null) {
	    expectToken(IdLiteral.class);
	}
	while (acceptToken(Dot.class) != null) {
	    if (acceptPart(MethodCall.class) == null) {
		expectToken(IdLiteral.class);
	    }
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
