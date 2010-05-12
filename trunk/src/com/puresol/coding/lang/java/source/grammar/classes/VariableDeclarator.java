package com.puresol.coding.lang.java.source.grammar.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.symbols.Assign;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableDeclarator extends AbstractJavaParser {

	private static final long serialVersionUID = -8995105296970831547L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		String name = getCurrentToken().getText();
		expectPart(VariableDeclaratorId.class);
		if (acceptToken(Assign.class) != null) {
			expectPart(VariableInitializer.class);
		}
		finish(name);
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
