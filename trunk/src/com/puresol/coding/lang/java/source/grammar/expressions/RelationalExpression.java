package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ReferenceType;
import com.puresol.coding.lang.java.source.keywords.InstanceofKeyword;
import com.puresol.coding.lang.java.source.symbols.GreaterEqual;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LessEqual;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class RelationalExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(ShiftExpression.class);
		if (acceptToken(LessThan.class) != null) {
			expectPart(RelationalExpression.class);
		} else if (acceptToken(GreaterThan.class) != null) {
			expectPart(RelationalExpression.class);
		} else if (acceptToken(LessEqual.class) != null) {
			expectPart(RelationalExpression.class);
		} else if (acceptToken(GreaterEqual.class) != null) {
			expectPart(RelationalExpression.class);
		} else if (acceptToken(InstanceofKeyword.class) != null) {
			expectPart(ReferenceType.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
