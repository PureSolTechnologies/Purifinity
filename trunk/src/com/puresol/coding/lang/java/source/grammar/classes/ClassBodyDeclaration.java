package com.puresol.coding.lang.java.source.grammar.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassBodyDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -2656071830287957232L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ClassMemberDeclaration.class) != null) {
		} else if (acceptPart(InstanceInitializer.class) != null) {
		} else if (acceptPart(StaticInitializer.class) != null) {
		} else if (acceptPart(ConstructorDeclaration.class) != null) {
		} else {
			throw new PartDoesNotMatchException(this);
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
