package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodDeclarator extends AbstractJavaParser {

    private static final long serialVersionUID = 7410581812232089806L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(Identifier.class);
	expectToken(LParen.class);
	acceptPart(FormalParameterList.class);
	expectToken(RParen.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
