package com.puresol.coding.lang.java.source.parts.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.parts.arrays.ArrayInitializer;
import com.puresol.coding.lang.java.source.parts.classes.ClassBody;
import com.puresol.coding.lang.java.source.parts.types_values_variables.ClassOrInterfaceType;
import com.puresol.coding.lang.java.source.parts.types_values_variables.PrimitiveType;
import com.puresol.coding.lang.java.source.parts.types_values_variables.TypeArguments;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
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
