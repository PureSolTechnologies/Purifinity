package com.puresol.coding.lang.java.source.parts.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.CaseKeyword;
import com.puresol.coding.lang.java.source.keywords.DefaultKeyword;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class SwitchLabel extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(DefaultKeyword.class) != null) {
		} else {
			expectToken(CaseKeyword.class);
			if (acceptPart(ConstantExpression.class) == null) {
				expectPart(EnumConstantName.class);
			}
		}
		expectToken(Colon.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
