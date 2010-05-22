package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * block : '{' (blockStatement )* '}' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Block extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(LCurlyBracket.class);
	acceptPart(BlockStatements.class);
	expectToken(RCurlyBracket.class);
	finish();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
