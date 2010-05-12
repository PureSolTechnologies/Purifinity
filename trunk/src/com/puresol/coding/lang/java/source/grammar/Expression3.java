package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Expression3 extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(PrefixOp.class) != null) {
			expectPart(Expression3.class);
		} else if (acceptPart(Expression.class) != null) {
			expectPart(Expression3.class);
		} else if (acceptPart(Type.class) != null) {
			expectPart(Expression3.class);
		} else {
			expectPart(Primary.class);
			while (acceptPart(Selector.class) != null)
				;
			while (acceptPart(PostfixOp.class) != null)
				;
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
