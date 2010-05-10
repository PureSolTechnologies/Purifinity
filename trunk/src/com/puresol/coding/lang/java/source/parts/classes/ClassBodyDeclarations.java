package com.puresol.coding.lang.java.source.parts.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassBodyDeclarations extends AbstractJavaParser {

	private static final long serialVersionUID = -2656071830287957232L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ClassBodyDeclaration.class);
		while (acceptPart(ClassBodyDeclaration.class) != null) {
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
