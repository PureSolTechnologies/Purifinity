package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.Expression;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * expression ';'
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExpressionStatement extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(Expression.class);
	expectToken(Semicolon.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
