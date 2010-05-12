package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ClassCreatorRest extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectPart(Arguments.class);
	acceptPart(ClassBody.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
