package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.Expression;
import com.puresol.coding.lang.java.source.grammar.expressions.ExpressionList;
import com.puresol.coding.lang.java.source.keywords.ForKeyword;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * 'for' '(' (forInit )? ';' (expression )? ';' (expressionList )? ')' statement
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class BasicForStatement extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(ForKeyword.class);
	expectToken(LParen.class);
	acceptPart(ForInit.class);
	expectToken(Semicolon.class);
	acceptPart(Expression.class);
	expectToken(Semicolon.class);
	acceptPart(ExpressionList.class);
	expectToken(RParen.class);
	expectPart(Statement.class);
	finish();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
