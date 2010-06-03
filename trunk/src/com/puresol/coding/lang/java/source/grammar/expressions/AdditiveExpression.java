package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Minus;
import com.puresol.coding.lang.java.source.symbols.Plus;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * additiveExpression : multiplicativeExpression ( ( '+' | '-' )
 * multiplicativeExpression )* ;
 * 
 * Fixed chaining!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AdditiveExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(MultiplicativeExpression.class);
		if (acceptToken(Plus.class) != null) {
			expectPart(AdditiveExpression.class);
		} else if (acceptToken(Minus.class) != null) {
			expectPart(AdditiveExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
