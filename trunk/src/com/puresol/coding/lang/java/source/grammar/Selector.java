package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Selector extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptToken(Dot.class) != null) {
	    if (acceptToken(Identifier.class) != null) {
		acceptPart(Arguments.class);
	    } else if (acceptPart(ExplicitGenericInvocation.class) != null) {

	    } else if (acceptToken(ThisKeyword.class) != null) {
	    } else if (acceptToken(SuperKeyword.class) != null) {
		expectPart(SuperSuffix.class);
	    } else {
		expectToken(NewKeyword.class);
		acceptPart(NonWildcardTypeArguments.class);
		expectPart(InnerCreator.class);
	    }
	} else {
	    expectToken(LRectBracket.class);
	    expectPart(Expression.class);
	    expectToken(RRectBracket.class);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
