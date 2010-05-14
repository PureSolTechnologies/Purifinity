package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.ExpressionList;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * arguments : '(' (expressionList )? ')' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Arguments extends AbstractJavaParser {

    private static final long serialVersionUID = -5500980743550485400L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LParen.class);
	acceptPart(ExpressionList.class);
	expectToken(RParen.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }
}
