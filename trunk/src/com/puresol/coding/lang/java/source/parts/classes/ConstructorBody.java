package com.puresol.coding.lang.java.source.parts.classes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.literals.IdLiteral;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.parts.blocks_and_statements.Block;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class ConstructorBody extends AbstractJavaParser {

	private static final long serialVersionUID = -5105706064635403458L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(ExplicitConstructorInvocation.class);
		expectPart(BlockStatements.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.CONSTRUCTOR;
	}

}
