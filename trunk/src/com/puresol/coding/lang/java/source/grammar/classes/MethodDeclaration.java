package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * For constructor, return type is null, name is 'init' methodDeclaration :
 * 
 * modifiers (typeParameters )? IDENTIFIER formalParameters ('throws'
 * qualifiedNameList )? '{' (explicitConstructorInvocation )? (blockStatement )*
 * '}' | modifiers (typeParameters )? (type | 'void' ) IDENTIFIER
 * formalParameters ('[' ']' )* ('throws' qualifiedNameList )? ( block | ';' ) ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class MethodDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = 7410581812232089806L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ConstructorDeclaration.class) != null) {
		} else if (acceptPart(NormalMethodDeclaration.class) != null) {
		} else {
			abort();
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
