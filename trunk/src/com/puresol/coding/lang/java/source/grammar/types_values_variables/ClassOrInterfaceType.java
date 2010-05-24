package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * classOrInterfaceType : IDENTIFIER (typeArguments )? ('.' IDENTIFIER
 * (typeArguments )? )* ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ClassOrInterfaceType extends AbstractJavaParser {

	private static final long serialVersionUID = 7523184950953085838L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Identifier.class);
		acceptPart(TypeArguments.class);
		while (acceptToken(Dot.class) != null) {
			expectToken(Identifier.class);
			acceptPart(TypeArguments.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
