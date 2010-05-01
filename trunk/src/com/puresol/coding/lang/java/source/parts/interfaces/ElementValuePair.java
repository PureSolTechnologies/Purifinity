package com.puresol.coding.lang.java.source.parts.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Assign;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ElementValuePair extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Identifier.class);
		expectToken(Assign.class);
		expectPart(ElementValue_not_finished.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
