package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class IdentifierSuffix extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(LRectBracket.class) != null) {
			if (acceptToken(RRectBracket.class) != null) {
				while (acceptToken(LRectBracket.class) != null) {
					expectToken(RRectBracket.class);
				}
			} else {
				expectPart(Expression.class);
				expectToken(RRectBracket.class);
			}
		} else if (acceptPart(Arguments.class) != null) {
		} else if (acceptToken(Dot.class) != null) {
			if (acceptToken(ClassKeyword.class) != null) {
			} else if (acceptPart(ExplicitGenericInvocation.class) != null) {
			} else if (acceptToken(ThisKeyword.class) != null) {
			} else if (acceptToken(SuperKeyword.class) != null) {
				expectPart(Arguments.class);
			} else {
				expectToken(NewKeyword.class);
				acceptPart(NonWildcardTypeArguments.class);
				expectPart(InnerCreator.class);
			}
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
