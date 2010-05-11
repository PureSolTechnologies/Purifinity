package com.puresol.coding.lang.java.source.parts.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.interfaces.InterfaceDeclaration;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassMemberDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -2656071830287957232L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(FieldDeclaration.class) != null) {
		} else if (acceptPart(MethodDeclaration.class) != null) {
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

	public List<FieldDeclaration> getFields() {
		return getChildCodeRanges(FieldDeclaration.class);
	}
}
