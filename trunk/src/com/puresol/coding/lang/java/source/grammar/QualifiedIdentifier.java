package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class QualifiedIdentifier extends AbstractJavaParser {

    private static final long serialVersionUID = 4903744780392938101L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(com.puresol.coding.lang.java.source.literals.Identifier.class);
	while (acceptToken(Dot.class) != null) {
	    expectToken(com.puresol.coding.lang.java.source.literals.Identifier.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }
}
