package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.GreaterThan;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.LessThan;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class NonWildcardTypeArguments extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LessThan.class);
	expectPart(TypeList.class);
	expectToken(GreaterThan.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
