package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.coding.lang.java.source.parts.expressions.AssignmentExpression;
import com.puresol.coding.lang.java.source.parts.expressions.Primary;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Primary extends AbstractJavaParser {

    private static final long serialVersionUID = 6464754895556318548L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptPart(ParExpression.class) != null) {
	} else if (acceptPart(NonWildTypeArguments.class) != null) {
	    if (acceptPart(ExplicitGenericInvocationSuffix.class) != null) {
	    } else if (acceptToken(ThisKeyword.class) != null) {
		expectPart(Arguments.class);
	    } else {
		throw new PartDoesNotMatchException(this);
	    }
	} else if (acceptToken(ThisKeyword.class) != null) {
	    acceptPart(Arguments.class);
	} else if (acceptToken(SuperKeyword.class) != null) {
	    expectPart(SuperSuffix.class);
	} else if (acceptPart(Literal.class) != null) {

	} else if (acceptToken(NewKeyword.class) != null) {
	    expectPart(Creator.class);
	} else if (acceptPart(Identifier.class) != null) {
	    while (acceptToken(Dot.class) != null) {
		expectPart(Identifier.class);
	    }
	    acceptPart(IdentifierSuffix.class);
	} else if (acceptPart(BasicType.class) != null) {
	    while (acceptToken(LRectBracket.class) != null) {
		expectToken(RRectBracket.class);
	    }
	} else if (acceptToken(VoidKeyword.class) != null) {
	    expectToken(Dot.class);
	    expectToken(VoidKeyword.class);
	} else {
	    throw new PartDoesNotMatchException(this);
	}
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
