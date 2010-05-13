package com.puresol.coding.lang.java.source.grammar.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.QualifiedName;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.TypeArguments;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class InterfaceType extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(QualifiedName.class);
		acceptPart(TypeArguments.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.CLASS;
	}

	public List<VariableDeclarator> getFields() {
		return getChildCodeRanges(ClassBody.class).get(0).getFields();
	}
}
