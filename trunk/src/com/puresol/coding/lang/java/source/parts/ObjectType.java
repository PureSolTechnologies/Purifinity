package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ObjectType extends AbstractJavaParser {

    private static final long serialVersionUID = 8377330018615004538L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(IdLiteral.class);
	while (acceptToken(Dot.class) != null) {
	    expectToken(IdLiteral.class);
	}
	acceptPart(Generic.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
