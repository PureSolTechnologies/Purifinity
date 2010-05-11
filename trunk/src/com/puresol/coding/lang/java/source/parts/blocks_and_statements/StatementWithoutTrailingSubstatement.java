package com.puresol.coding.lang.java.source.parts.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class StatementWithoutTrailingSubstatement extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Block.class) != null) {
		} else if (acceptPart(EmptyStatement.class) != null) {
		} else if (acceptPart(ExpressionStatement.class) != null) {
		} else if (acceptPart(AssertStatement.class) != null) {
		} else if (acceptPart(SwitchStatement.class) != null) {
		} else if (acceptPart(DoStatement.class) != null) {
		} else if (acceptPart(BreakStatement.class) != null) {
		} else if (acceptPart(ContinueStatement.class) != null) {
		} else if (acceptPart(ReturnStatement.class) != null) {
		} else if (acceptPart(SynchronizedStatement.class) != null) {
		} else if (acceptPart(ThrowStatement.class) != null) {
		} else {
			expectPart(TryStatement.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
