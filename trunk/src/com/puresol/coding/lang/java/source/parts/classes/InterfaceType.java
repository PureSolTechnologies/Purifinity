package com.puresol.coding.lang.java.source.parts.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.FieldDeclaration;
import com.puresol.coding.lang.java.source.parts.types_values_variables.TypeArguments;
import com.puresol.coding.lang.java.source.parts.types_values_variables.TypeDeclSpecifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class InterfaceType extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(TypeDeclSpecifier.class);
		expectPart(TypeArguments.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.CLASS;
	}

	public List<FieldDeclaration> getFields() {
		return getChildCodeRanges(ClassBody.class).get(0).getFields();
	}
}
