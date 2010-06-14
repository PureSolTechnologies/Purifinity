package com.puresol.coding.lang.java.source.grammar.classes;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class FieldModifiers extends AbstractJavaParser {

	private static final long serialVersionUID = 4903744780392938101L;

	private final List<String> modifiers = new ArrayList<String>();

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(FieldModifier.class);
		while (acceptPart(FieldModifier.class) != null) {
		}
		finish();
		List<CodeRange> children = getChildCodeRanges();
		for (CodeRange child : children) {
			modifiers.add(child.getText());
		}
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	public List<String> getModifiers() {
		return modifiers;
	}
}
