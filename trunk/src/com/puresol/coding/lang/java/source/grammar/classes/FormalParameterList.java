package com.puresol.coding.lang.java.source.grammar.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.Token;

public class FormalParameterList extends AbstractJavaParser {

    private static final long serialVersionUID = -1812295859556451418L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	try {
	    Token nextToken = getTokenStream().findNextToken(
		    getCurrentPosition());
	    while (nextToken.getDefinition().equals(Comma.class)) {
		expectPart(FormalParameter.class);
		nextToken = getTokenStream()
			.findNextToken(getCurrentPosition());
	    }
	    expectPart(LastFormalParameter.class);
	    finish();
	} catch (NoMatchingTokenException e) {
	    throw new PartDoesNotMatchException(this);
	}
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.CLASS;
    }

    public List<VariableDeclarator> getFields() {
	return getChildCodeRanges(ClassBody.class).get(0).getFields();
    }
}
