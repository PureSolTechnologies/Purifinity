package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.MethodModifiers;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * annotationMethodDeclaration : modifiers type IDENTIFIER '(' ')' ('default'
 * elementValue )? ';' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AnnotationMethodDeclaration extends AbstractJavaParser {

	private static final long serialVersionUID = -2656071830287957232L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(MethodModifiers.class);
		expectPart(Type.class);
		expectToken(Identifier.class);
		expectToken(LParen.class);
		expectToken(RParen.class);
		acceptPart(DefaultValue.class);
		expectToken(Semicolon.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}
}
