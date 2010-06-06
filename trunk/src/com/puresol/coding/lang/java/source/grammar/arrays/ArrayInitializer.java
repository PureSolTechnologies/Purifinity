package com.puresol.coding.lang.java.source.grammar.arrays;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.VariableInitializer;
import com.puresol.coding.lang.java.source.symbols.Comma;
import com.puresol.coding.lang.java.source.symbols.LCurlyBracket;
import com.puresol.coding.lang.java.source.symbols.RCurlyBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * arrayInitializer 
 *     :   '{' 
 *             (variableInitializer
 *                 (',' variableInitializer
 *                 )*
 *             )? 
 *             (',')? 
 *         '}'             //Yang's fix, position change.
 *     ;
 *</pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ArrayInitializer extends AbstractJavaParser {

	private static final long serialVersionUID = 4903744780392938101L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LCurlyBracket.class);
		if (acceptPart(VariableInitializer.class) != null) {
			while (acceptToken(Comma.class) != null) {
				if (acceptPart(VariableInitializer.class) == null) {
					break;
				}
			}
		} else {
			acceptToken(Comma.class);
		}
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
