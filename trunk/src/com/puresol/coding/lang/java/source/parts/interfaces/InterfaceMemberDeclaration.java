package com.puresol.coding.lang.java.source.parts.interfaces;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.classes.ClassDeclaration;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class InterfaceMemberDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -2656071830287957232L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ConstantDeclaration.class) != null) {
		} else if (acceptPart(AbstractMethodDeclaration.class) != null) {
		} else if (acceptPart(ClassDeclaration.class) != null) {
		} else if (acceptPart(InterfaceDeclaration.class) != null) {
		} else {
			expectToken(Semicolon.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
