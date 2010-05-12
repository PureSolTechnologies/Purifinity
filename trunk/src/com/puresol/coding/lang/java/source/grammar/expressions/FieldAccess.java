package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ClassName;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class FieldAccess extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Primary.class) != null) {
		} else if (acceptToken(SuperKeyword.class) != null) {
		} else {
			expectPart(ClassName.class);
			expectToken(Dot.class);
			expectToken(SuperKeyword.class);
		}
		expectToken(Dot.class);
		expectToken(Identifier.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
