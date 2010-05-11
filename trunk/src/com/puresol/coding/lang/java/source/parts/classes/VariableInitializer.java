package com.puresol.coding.lang.java.source.parts.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.arrays.ArrayInitializer;
import com.puresol.coding.lang.java.source.parts.types_values_variables.Type;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableInitializer extends AbstractJavaParser {

	private static final long serialVersionUID = -8995105296970831547L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Expression.class) != null) {
		} else {
			expectPart(ArrayInitializer.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	public List<String> getModifiers() {
		FieldModifiers modifiers = getChildCodeRanges(FieldModifiers.class)
				.get(0);
		return modifiers.getModifiers();
	}

	public String getVariableType() {
		Type type = getChildCodeRanges(Type.class).get(0);
		return type.getVariableTypeName();
	}
}
