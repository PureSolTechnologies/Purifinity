package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.blocks_and_statements.BlockStatements;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * For constructor, return type is null, name is 'init' methodDeclaration :
 * 
 * modifiers (typeParameters )? IDENTIFIER formalParameters ('throws'
 * qualifiedNameList )? '{' (explicitConstructorInvocation )? (blockStatement )*
 * '}'
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ConstructorDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = 7410581812232089806L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(MethodModifiers.class);
		acceptPart(TypeParameters.class);
		expectToken(Identifier.class);
		expectPart(FormalParameters.class);
		acceptPart(Throws.class);
		expectToken(LCurlyBracket.class);
		acceptPart(ExplicitConstructorInvocation.class);
		acceptPart(BlockStatements.class);
		expectToken(RCurlyBracket.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.METHOD;
	}

}
