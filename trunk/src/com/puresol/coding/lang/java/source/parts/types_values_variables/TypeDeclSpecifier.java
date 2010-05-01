package com.puresol.coding.lang.java.source.parts.types_values_variables;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class TypeDeclSpecifier extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		try {
			if (lookAhead(1).getDefinition().equals(Dot.class)) {
				expectPart(ClassOrInterfaceType.class);
				expectToken(Dot.class);
				expectToken(Identifier.class);
			} else {
				expectPart(TypeName.class);
			}
			finish();
		} catch (EndOfTokenStreamException e) {
			abort();
		}
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	public String getVariableTypeName() {
		return getContinuousText();
	}
}
