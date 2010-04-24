package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableName extends AbstractJavaParser {

	private static final long serialVersionUID = -1698959058987391804L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(IdLiteral.class);
		if (acceptToken(LRectBracket.class)) {
			expectToken(RRectBracket.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
