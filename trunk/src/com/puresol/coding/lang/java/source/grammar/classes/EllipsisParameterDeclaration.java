package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * ellipsisParameterDecl : variableModifiers type '...' IDENTIFIER ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EllipsisParameterDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = -1812295859556451418L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	acceptPart(VariableModifiers.class);
	expectPart(Type.class);
	expectToken(Dot.class);
	expectToken(Dot.class);
	expectToken(Dot.class);
	expectToken(Identifier.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.CLASS;
    }
}
