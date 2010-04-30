package com.puresol.coding.lang.java.source.parts;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.Assign;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class VariableAssignment extends AbstractJavaParser {

    private static final long serialVersionUID = -1698959058987391804L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(Variable.class);
	expectToken(Assign.class);
	expectPart(Term.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
