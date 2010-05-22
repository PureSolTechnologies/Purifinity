package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.VariableModifiers;
import com.puresol.coding.lang.java.source.grammar.expressions.Dims;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * formalParameter : variableModifiers type IDENTIFIER ('[' ']' )* ;
 * 
 * Fix: Changes variableModifiers to acceptPart due to different implementation.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FormalParameter extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	acceptPart(VariableModifiers.class);
	expectPart(Type.class);
	expectToken(Identifier.class);
	acceptPart(Dims.class);
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
