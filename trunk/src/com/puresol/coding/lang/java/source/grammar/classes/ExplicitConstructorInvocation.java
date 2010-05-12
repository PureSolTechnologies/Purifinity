package com.puresol.coding.lang.java.source.grammar.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.ArgumentList;
import com.puresol.coding.lang.java.source.grammar.expressions.Primary;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.coding.lang.java.source.symbols.Semicolon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ExplicitConstructorInvocation extends AbstractJavaParser {

	private static final long serialVersionUID = -5105706064635403458L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Primary.class) != null) {
			acceptPart(NonWildTypeArguments.class);
			expectToken(SuperKeyword.class);
		} else {
			acceptPart(NonWildTypeArguments.class);
			if (acceptToken(SuperKeyword.class) != null) {
			} else {
				expectToken(ThisKeyword.class);
			}
		}
		expectToken(LParen.class);
		acceptPart(ArgumentList.class);
		expectToken(RParen.class);
		expectToken(Semicolon.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.CONSTRUCTOR;
	}

}
