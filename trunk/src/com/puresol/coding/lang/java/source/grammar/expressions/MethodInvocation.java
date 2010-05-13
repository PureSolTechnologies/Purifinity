package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.NonWildTypeArguments;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.QualifiedName;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class MethodInvocation extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(QualifiedName.class) != null) {
			expectToken(LParen.class);
			acceptPart(ArgumentList.class);
			expectToken(RParen.class);
		} else if (acceptPart(Primary.class) != null) {
			expectToken(Dot.class);
			acceptPart(NonWildTypeArguments.class);
			expectToken(Identifier.class);
			expectToken(LParen.class);
			acceptPart(ArgumentList.class);
			expectToken(RParen.class);
		} else if (acceptToken(SuperKeyword.class) != null) {
			expectToken(Dot.class);
			acceptPart(NonWildTypeArguments.class);
			expectToken(Identifier.class);
			expectToken(LParen.class);
			acceptPart(ArgumentList.class);
			expectToken(RParen.class);
		} else if (acceptPart(QualifiedName.class) != null) {
			expectToken(Dot.class);
			expectToken(SuperKeyword.class);
			expectToken(Dot.class);
			acceptPart(NonWildTypeArguments.class);
			expectToken(Identifier.class);
			expectToken(LParen.class);
			acceptPart(ArgumentList.class);
			expectToken(RParen.class);
		} else if (acceptPart(QualifiedName.class) != null) {
			expectToken(Dot.class);
			expectPart(NonWildTypeArguments.class);
			expectToken(Identifier.class);
			expectToken(LParen.class);
			acceptPart(ArgumentList.class);
			expectToken(RParen.class);
		} else {
			abort();
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
