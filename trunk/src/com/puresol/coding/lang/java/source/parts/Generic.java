package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Generic extends AbstractJavaParser {

	private static final long serialVersionUID = -9058748589224609619L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		skipNested(LessThan.class, GreaterThan.class); // TODO
		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
