package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.VariableDeclarators;
import com.puresol.coding.lang.java.source.grammar.classes.VariableModifiers;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * localVariableDeclaration : variableModifiers type variableDeclarator (','
 * variableDeclarator )* ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LocalVariableDeclaration extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	acceptPart(VariableModifiers.class);
	expectPart(Type.class);
	expectPart(VariableDeclarators.class);
	finish();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
