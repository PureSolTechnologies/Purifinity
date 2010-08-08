package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.arrays.ArrayInitializer;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.CreatedName;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.tokens.EndOfTokenStreamException;

/**
 * <pre>
 * arrayCreator 
 *     :   'new' createdName
 *         '[' ']'
 *         ('[' ']'
 *         )*
 *         arrayInitializer
 * 
 *     |   'new' createdName
 *         '[' expression
 *         ']'
 *         (   '[' expression
 *             ']'
 *         )*
 *         ('[' ']'
 *         )*
 *     ;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ArrayCreator extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(NewKeyword.class);
		expectPart(CreatedName.class);
		if (acceptPart(Dim.class) != null) {
			acceptPart(Dims.class);
			expectPart(ArrayInitializer.class);
		} else {
			expectToken(LRectBracket.class);
			expectPart(Expression.class);
			expectToken(RRectBracket.class);
			try {
				while (!lookAhead(1).getDefinition().equals(RRectBracket.class)) {
					if (acceptToken(LRectBracket.class) != null) {
						expectPart(Expression.class);
						expectToken(RRectBracket.class);
					} else {
						break;
					}
				}
			} catch (EndOfTokenStreamException e) {
			}
			acceptPart(Dims.class);
		}
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
