package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.CatchKeyword;
import com.puresol.coding.lang.java.source.symbols.LParen;
import com.puresol.coding.lang.java.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

public class CatchClause extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(CatchKeyword.class);
		expectToken(LParen.class);
		expectPart(FormalParameter.class);
		expectToken(RParen.class);
		expectPart(Block.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
