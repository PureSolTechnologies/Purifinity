package com.puresol.coding.lang.java.source.grammar.types_values_variables;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.Dims;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * type : classOrInterfaceType ('[' ']' )* | primitiveType ('[' ']' )* ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Type extends AbstractJavaParser {

    private static final long serialVersionUID = 7523184950953085838L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(ClassOrInterfaceType.class) != null) {
	    acceptPart(Dims.class);
	} else if (acceptPart(PrimitiveType.class) != null) {
	    acceptPart(Dims.class);
	} else {
	    abort();
	}
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
