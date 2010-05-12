package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.literals.BooleanLiteral;
import com.puresol.coding.lang.java.source.literals.CharacterLiteral;
import com.puresol.coding.lang.java.source.literals.FloatingPointLiteral;
import com.puresol.coding.lang.java.source.literals.IntegerLiteral;
import com.puresol.coding.lang.java.source.literals.NullLiteral;
import com.puresol.coding.lang.java.source.literals.StringLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class Literal extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(IntegerLiteral.class) != null) {
		} else if (acceptToken(FloatingPointLiteral.class) != null) {
		} else if (acceptToken(BooleanLiteral.class) != null) {
		} else if (acceptToken(CharacterLiteral.class) != null) {
		} else if (acceptToken(StringLiteral.class) != null) {
		} else {
			expectToken(NullLiteral.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
