package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.LogicalOr;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * conditionalOrExpression : conditionalAndExpression ('||'
 * conditionalAndExpression )* ;
 * 
 * Fixed Chaining!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ConditionalOrExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ConditionalAndExpression.class);
		if (acceptToken(LogicalOr.class) != null) {
			expectPart(ConditionalOrExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
