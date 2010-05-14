package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.blocks_and_statements.Block;
import com.puresol.coding.lang.java.source.grammar.blocks_and_statements.BlockStatements;
import com.puresol.coding.lang.java.source.grammar.expressions.Dims;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
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
	acceptPart(MethodModifiers.class);
	acceptPart(TypeParameters.class);
	if (getCurrentToken().getDefinition().equals(Identifier.class)) {
	    // constructor...
	    expectToken(Identifier.class);
	    expectPart(FormalParameters.class);
	    acceptPart(Throws.class);
	    expectToken(LCurlyBracket.class);
	    acceptPart(ExplicitConstructorInvocation.class);
	    acceptPart(BlockStatements.class);
	    expectToken(RCurlyBracket.class);
	} else {
	    // method...
	    if (acceptPart(Type.class) != null) {
	    } else if (acceptToken(VoidKeyword.class) != null) {
	    } else {
		abort();
	    }
	    expectToken(Identifier.class);
	    expectPart(FormalParameters.class);
	    acceptPart(Dims.class);
	    acceptPart(Throws.class);
	    if (acceptPart(Block.class) != null) {
	    } else if (acceptToken(Semicolon.class) != null) {
	    } else {
		abort();
	    }
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
