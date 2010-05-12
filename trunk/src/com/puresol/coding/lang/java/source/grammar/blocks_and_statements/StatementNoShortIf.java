package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class StatementNoShortIf extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(StatementWithoutTrailingSubstatement.class) != null) {
		} else if (acceptPart(LabeledStatementNoShortIf.class) != null) {
		} else if (acceptPart(IfThenElseStatementNoShortIf.class) != null) {
		} else if (acceptPart(WhileStatementNoShortIf.class) != null) {
		} else {
			expectPart(ForStatementNoShortIf.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
