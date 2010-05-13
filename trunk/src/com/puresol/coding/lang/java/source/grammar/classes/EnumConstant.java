package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.interfaces.Annotations;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class EnumConstant extends AbstractJavaParser {

	private static final long serialVersionUID = -5500980743550485400L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		/*
		 * Maybe there is a failure in the Java Language Specification for
		 * annotations on enum constants. The specification tells it is
		 * mandatory, but this should be wrong.
		 */
		acceptPart(Annotations.class);
		expectToken(Identifier.class);
		acceptPart(Arguments.class);
		acceptPart(ClassBody.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
