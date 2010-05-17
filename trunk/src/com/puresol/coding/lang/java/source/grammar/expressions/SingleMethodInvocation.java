package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.NonWildcardTypeArguments;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class SingleMethodInvocation extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Dot.class);
		acceptPart(NonWildcardTypeArguments.class);
		expectToken(Identifier.class);
		expectToken(LParen.class);
		acceptPart(ExpressionList.class);
		expectToken(RParen.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
