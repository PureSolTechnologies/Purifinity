package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.Assignment;
import com.puresol.coding.lang.java.source.grammar.expressions.ClassInstanceCreationExpression;
import com.puresol.coding.lang.java.source.grammar.expressions.MethodInvocation;
import com.puresol.coding.lang.java.source.grammar.expressions.PostDecrementExpression;
import com.puresol.coding.lang.java.source.grammar.expressions.PostIncrementExpression;
import com.puresol.coding.lang.java.source.grammar.expressions.PreDecrementExpression;
import com.puresol.coding.lang.java.source.grammar.expressions.PreIncrementExpression;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class StatementExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Assignment.class) != null) {
		} else if (acceptPart(Assignment.class) != null) {
		} else if (acceptPart(PreIncrementExpression.class) != null) {
		} else if (acceptPart(PreDecrementExpression.class) != null) {
		} else if (acceptPart(PostIncrementExpression.class) != null) {
		} else if (acceptPart(PostDecrementExpression.class) != null) {
		} else if (acceptPart(MethodInvocation.class) != null) {
		} else {
			expectPart(ClassInstanceCreationExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
