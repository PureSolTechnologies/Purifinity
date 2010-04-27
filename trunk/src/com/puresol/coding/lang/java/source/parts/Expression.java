package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Minus;
import com.puresol.coding.lang.java.source.symbols.Plus;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Expression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(Plus.class)) {
		} else if (acceptToken(Minus.class)) {
		}
		expectPart(Term.class);
		while (acceptToken(Plus.class) || (acceptToken(Minus.class))) {
			expectPart(Term.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
