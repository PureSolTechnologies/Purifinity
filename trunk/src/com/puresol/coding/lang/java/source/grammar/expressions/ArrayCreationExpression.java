package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.arrays.ArrayInitializer;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ClassOrInterfaceType;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.PrimitiveType;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ArrayCreationExpression extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(NewKeyword.class);
		if (acceptPart(PrimitiveType.class) != null) {
		} else {
			expectPart(ClassOrInterfaceType.class);
		}
		if (acceptPart(DimExprs.class) != null) {
			acceptPart(Dims.class);
		} else {
			expectPart(Dims.class);
			expectPart(ArrayInitializer.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
