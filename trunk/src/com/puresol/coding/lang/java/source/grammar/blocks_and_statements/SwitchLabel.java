package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.expressions.Expression;
import com.puresol.coding.lang.java.source.keywords.CaseKeyword;
import com.puresol.coding.lang.java.source.keywords.DefaultKeyword;
import com.puresol.coding.lang.java.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * switchLabel : 'case' expression ':' | 'default' ':' ;
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SwitchLabel extends AbstractJavaParser {

    private static final long serialVersionUID = 1202904051316374607L;

    @Override
    public void scan() throws PartDoesNotMatchException, ParserException {
	if (acceptToken(CaseKeyword.class) != null) {
	    expectPart(Expression.class);
	} else if (acceptToken(DefaultKeyword.class) != null) {
	} else {
	    abort();
	}
	expectToken(Colon.class);
	finish();
    }

    @Override
    public CodeRangeType getCodeRangeType() {
	return CodeRangeType.FRAGMENT;
    }

}
