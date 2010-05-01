package com.puresol.coding.lang.java.source.parts.classes;

import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.parts.FieldDeclaration;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class TypeParameters extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LessThan.class);
		expectToken(Identifier.class);
		while (acceptToken(Comma.class) != null) {
			expectToken(Identifier.class);
		}
		expectToken(GreaterThan.class);
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
