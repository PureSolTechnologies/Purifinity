package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.MethodDeclarator;
import com.puresol.coding.lang.java.source.grammar.classes.ResultType;
import com.puresol.coding.lang.java.source.grammar.classes.Throws;
import com.puresol.coding.lang.java.source.grammar.classes.TypeParameters;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class AbstractMethodDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -2656071830287957232L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(AbstractMethodModifiers.class);
		acceptPart(TypeParameters.class);
		expectPart(ResultType.class);
		expectPart(MethodDeclarator.class);
		expectPart(Throws.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
