package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;

import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.ParExpression;
import com.puresol.coding.lang.java.source.keywords.ElseKeyword;
import com.puresol.coding.lang.java.source.keywords.IfKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * 'if' parExpression statement ('else' statement)?
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IfThenElseStatement extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(IfKeyword.class);
	expectPart(ParExpression.class);
	expectPart(Statement.class);
	if (acceptToken(ElseKeyword.class) != null) {
	    expectPart(Statement.class);
	}
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
