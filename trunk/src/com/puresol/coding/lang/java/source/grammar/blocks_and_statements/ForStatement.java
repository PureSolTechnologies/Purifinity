package com.puresol.coding.lang.java.source.grammar.blocks_and_statements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * forstatement 
 *     :   
 *         // enhanced for loop
 *         'for' '(' variableModifiers type IDENTIFIER ':' 
 *         expression ')' statement
 *             
 *         // normal for loop
 *     |   'for' '(' 
 *                 (forInit
 *                 )? ';' 
 *                 (expression
 *                 )? ';' 
 *                 (expressionList
 *                 )? ')' statement
 *     ;
 *</pre>
 * 
 * @author ludwig
 * 
 */
public class ForStatement extends AbstractJavaParser {

	private static final long serialVersionUID = 1202904051316374607L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(BasicForStatement.class) != null) {
		} else if (acceptPart(EnhancedForStatement.class) != null) {
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
