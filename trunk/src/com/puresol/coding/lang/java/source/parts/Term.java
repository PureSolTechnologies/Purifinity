package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Slash;
import com.puresol.coding.lang.java.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Term extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(Factor.class);
		while (acceptToken(Star.class) || acceptToken(Slash.class)) {
			expectPart(Factor.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
