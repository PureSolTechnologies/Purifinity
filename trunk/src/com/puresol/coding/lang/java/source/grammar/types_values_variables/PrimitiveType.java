package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.tokengroups.Primitives;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * primitiveType : 'boolean' | 'char' | 'byte' | 'short' | 'int' | 'long' |
 * 'float' | 'double' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PrimitiveType extends AbstractJavaParser {

    private static final long serialVersionUID = 7523184950953085838L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectOneTokenOf(Primitives.DEFINITIONS);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

    public String getVariableTypeName() {
	return getContinuousText();
    }
}
