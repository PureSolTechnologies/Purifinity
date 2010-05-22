package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.keywords.TryKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * trystatement 
 *     :   'try' block
 *         (   catches 'finally' block
 *         |   catches
 *         |   'finally' block
 *         )
 *      ;
 *</pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TryStatement extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	expectToken(TryKeyword.class);
	expectPart(Block.class);
	if (acceptPart(Catches.class) != null) {
	    acceptPart(Finally.class);
	} else {
	    expectPart(Finally.class);
	}
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
