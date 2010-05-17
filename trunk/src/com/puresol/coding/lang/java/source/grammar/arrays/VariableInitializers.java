package com.puresol.coding.lang.java.source.grammar.arrays;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.VariableInitializer;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * variableInitializer (',' variableInitializer )*
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class VariableInitializers extends AbstractJavaParser {

    private static final long serialVersionUID = -8995105296970831547L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(VariableInitializer.class);
	while (acceptToken(Comma.class) != null) {
	    expectPart(VariableInitializer.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }
}
