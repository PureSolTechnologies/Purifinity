package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class InterfaceMemberDeclarations extends AbstractJavaParser {

	private static final long serialVersionUID = -5845018908537488666L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(InterfaceMemberDeclaration.class);
		while (acceptPart(InterfaceMemberDeclaration.class) != null) {
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
