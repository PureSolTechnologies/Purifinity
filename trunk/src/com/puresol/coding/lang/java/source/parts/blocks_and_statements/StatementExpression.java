package com.puresol.coding.lang.java.source.parts.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
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
			exceptPart(ClassInstanceCreationExpression.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
