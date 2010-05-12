package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Type extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(BasicType.class) != null) {
		} else {
			expectToken(Identifier.class);
			acceptPart(TypeArguments.class);
			while (acceptToken(Dot.class) != null) {
				expectToken(Identifier.class);
				acceptPart(TypeArguments.class);
			}
			while (acceptToken(LRectBracket.class) != null) {
				expectToken(RRectBracket.class);
			}
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
