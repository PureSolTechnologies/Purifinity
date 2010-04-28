package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.StaticKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class StaticBlock extends AbstractJavaParser {

	private static final long serialVersionUID = -5505554029543650160L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(StaticKeyword.class);
		expectPart(CodeBlock.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
