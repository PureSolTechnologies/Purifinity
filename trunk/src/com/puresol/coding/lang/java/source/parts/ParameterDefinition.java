package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.types_values_variables.Type;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ParameterDefinition extends AbstractJavaParser {

    private static final long serialVersionUID = -3056829239936811187L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LParen.class);
	if (acceptPart(Type.class) != null) {
	    expectPart(VariableName.class);
	    while (acceptToken(Comma.class) != null) {
		expectPart(Type.class);
		expectPart(VariableName.class);
	    }
	}
	expectToken(RParen.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
