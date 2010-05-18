package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.Token;

/**
 * qualifiedImportName : IDENTIFIER ('.' IDENTIFIER )* ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class QualifiedName extends AbstractJavaParser {

    private static final long serialVersionUID = 7523184950953085838L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	try {
	    expectToken(Identifier.class);
	    Token currentToken = getCurrentToken();
	    if (currentToken != null) {
		while (currentToken.getDefinition().equals(Dot.class)) {
		    Token nextToken = getTokenStream().findNextToken(
			    getCurrentPosition());
		    if (nextToken.getDefinition().equals(Identifier.class)) {
			expectToken(Dot.class);
			expectToken(Identifier.class);
		    } else {
			break;
		    }
		    currentToken = getCurrentToken();
		    if (currentToken == null) {
			break;
		    }
		}
	    }
	    finish();
	} catch (EndOfTokenStreamException e) {
	    throw new PartDoesNotMatchException(this);
	} catch (NoMatchingTokenException e) {
	    throw new PartDoesNotMatchException(this);
	}
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

    public String getVariableTypeName() {
	return getVisibleText();
    }
}
