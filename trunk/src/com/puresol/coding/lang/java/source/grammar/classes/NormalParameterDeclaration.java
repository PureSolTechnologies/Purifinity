package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.Dims;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * normalParameterDecl : variableModifiers type IDENTIFIER ('[' ']' )* ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class NormalParameterDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -1812295859556451418L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(VariableModifiers.class);
		expectPart(Type.class);
		expectToken(Identifier.class);
		acceptPart(Dims.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
