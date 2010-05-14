package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class SimpleTypeName extends AbstractJavaParser {

    private static final long serialVersionUID = -5105706064635403458L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	acceptPart(TypeParameters.class);
	String name = getCurrentToken().getText();
	expectToken(Identifier.class);
	expectPart(FormalParameters.class);
	finish(name);
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.CONSTRUCTOR;
    }

}
