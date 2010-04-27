package com.puresol.coding.lang.java.source.parts;

import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.cpp.source.literals.IntegerLiteral;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.FloatingPointLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenDefinition;

public class NumericalConstant extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	private static final List<Class<? extends TokenDefinition>> classes = new ArrayList<Class<? extends TokenDefinition>>();
	static {
		classes.add(IntegerLiteral.class);
		classes.add(FloatingPointLiteral.class);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectOneOf(classes);
		finish();
	}

	@Override
	public CodeRangeType getType() {
		return CodeRangeType.FRAGMENT;
	}

}
