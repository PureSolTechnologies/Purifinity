package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.ClassBody;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ClassOrInterfaceType;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.TypeArguments;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * This is a part of the original ClassInstanceCreationExpression. It's the
 * unqualified part.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class UnqualifiedClassInstanceCreationExpression extends
		AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(NewKeyword.class);
		acceptPart(TypeArguments.class);
		expectPart(ClassOrInterfaceType.class);
		expectToken(LParen.class);
		acceptPart(ArgumentList.class);
		expectToken(RParen.class);
		acceptPart(ClassBody.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
