package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ClassName;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.Type;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.keywords.VoidKeyword;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class PrimaryNoNewArray extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(Literal.class) != null) {
		} else if (acceptToken(VoidKeyword.class) != null) {
			expectToken(Dot.class);
			expectToken(ClassKeyword.class);
		} else if (acceptToken(ThisKeyword.class) != null) {
		} else if (acceptToken(LParen.class) != null) {
			expectPart(Expression.class);
			expectToken(RParen.class);
		} else if (acceptPart(ClassInstanceCreationExpression.class) != null) {
		} else if (acceptPart(FieldAccess.class) != null) {
		} else if (acceptPart(MethodInvocation.class) != null) {
		} else if (acceptPart(ArrayAccess.class) != null) {
		} else if (acceptPart(Type.class) != null) {
			expectToken(Dot.class);
			expectToken(ClassKeyword.class);
		} else {
			expectPart(ClassName.class);
			expectToken(Dot.class);
			expectToken(ThisKeyword.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
