package com.puresol.coding.lang.java.source.parts.packages;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class PackageName extends AbstractJavaParser {

	private static final long serialVersionUID = -3655022757942995084L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Identifier.class);
		while (acceptToken(Dot.class) != null) {
			expectToken(Identifier.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
