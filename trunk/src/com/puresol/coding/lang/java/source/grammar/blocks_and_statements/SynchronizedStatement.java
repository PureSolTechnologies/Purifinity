package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.ParExpression;
import com.puresol.coding.lang.java.source.keywords.SynchronizedKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * 'synchronized' parExpression block
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SynchronizedStatement extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(SynchronizedKeyword.class);
	expectPart(ParExpression.class);
	expectPart(Block.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
