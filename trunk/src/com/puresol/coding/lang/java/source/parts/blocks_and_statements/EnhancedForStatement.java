package com.puresol.coding.lang.java.source.parts.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.ForKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.parts.Expression;
import com.puresol.coding.lang.java.source.parts.classes.VariableModifiers;
import com.puresol.coding.lang.java.source.parts.types_values_variables.Type;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class EnhancedForStatement extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ForKeyword.class);
		expectToken(LParen.class);
		acceptPart(VariableModifiers.class);
		expectPart(Type.class);
		expectToken(Identifier.class);
		expectToken(Colon.class);
		expectPart(Expression.class);
		expectToken(RParen.class);
		expectPart(Statement.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
