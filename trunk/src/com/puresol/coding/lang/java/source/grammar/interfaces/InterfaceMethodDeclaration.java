package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.FormalParameters;
import com.puresol.coding.lang.java.source.grammar.classes.MethodModifiers;
import com.puresol.coding.lang.java.source.grammar.classes.Throws;
import com.puresol.coding.lang.java.source.grammar.classes.TypeParameters;
import com.puresol.coding.lang.java.source.grammar.expressions.Dims;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * interfaceMethodDeclaration : modifiers (typeParameters )? (type |'void' )
 * IDENTIFIER formalParameters ('[' ']' )* ('throws' qualifiedNameList )? ';' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InterfaceMethodDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -272288459480520101L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(MethodModifiers.class);
		acceptPart(TypeParameters.class);
		if (acceptPart(Type.class) != null) {
		} else if (acceptToken(VoidKeyword.class) != null) {
		} else {
			abort();
		}
		expectToken(Identifier.class);
		expectPart(FormalParameters.class);
		acceptPart(Dims.class);
		acceptPart(Throws.class);
		expectToken(Semicolon.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.METHOD;
	}

}
