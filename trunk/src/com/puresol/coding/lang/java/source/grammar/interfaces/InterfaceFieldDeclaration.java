package com.puresol.coding.lang.java.source.grammar.interfaces;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.FieldModifiers;
import com.puresol.coding.lang.java.source.grammar.classes.VariableDeclarators;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * NOTE, should not use variableDeclarator here, as it doesn't necessary require
 * an initializer, while an interface field does, or judge by the returned
 * value.
 * 
 * But this gives better diagnostic message, or antlr won't predict this rule.
 * 
 * interfaceFieldDeclaration : modifiers type variableDeclarator (','
 * variableDeclarator )* ';' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InterfaceFieldDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -2656071830287957232L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	acceptPart(FieldModifiers.class);
	expectPart(Type.class);
	expectPart(VariableDeclarators.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }
}
