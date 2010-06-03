package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Caret;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * exclusiveOrExpression : andExpression ('^' andExpression )* ;
 * 
 * Fixed chaining!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ExclusiveOrExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(AndExpression.class);
		if (acceptToken(Caret.class) != null) {
			expectPart(ExclusiveOrExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
