package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.Arguments;
import com.puresol.coding.lang.java.source.grammar.classes.NonWildcardTypeArguments;
import com.puresol.coding.lang.java.source.keywords.ClassKeyword;
import com.puresol.coding.lang.java.source.keywords.SuperKeyword;
import com.puresol.coding.lang.java.source.keywords.ThisKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.coding.lang.java.source.symbols.LRectBracket;
import com.puresol.coding.lang.java.source.symbols.RRectBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * identifierSuffix 
 *     :   ('[' ']'
 *         )+
 *         '.' 'class'
 *     |   ('[' expression ']'
 *         )+
 *     |   arguments
 *     |   '.' 'class'
 *     |   '.' nonWildcardTypeArguments IDENTIFIER arguments
 *     |   '.' 'this'
 *     |   '.' 'super'           FIX: --arguments-- --> superSuffix!
 *     |   innerCreator
 *     ;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IdentifierSuffix extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(LRectBracket.class) != null) {
			if (acceptToken(RRectBracket.class) != null) {
				acceptPart(Dims.class);
				expectToken(Dot.class);
				expectToken(ClassKeyword.class);
			} else if (acceptPart(Expression.class) != null) {
				expectToken(RRectBracket.class);
				while (acceptToken(LRectBracket.class) != null) {
					expectPart(Expression.class);
					expectToken(RRectBracket.class);
				}
			} else {
				abort();
			}
		} else if (acceptPart(Arguments.class) != null) {
		} else if (acceptToken(Dot.class) != null) {
			if (acceptToken(ClassKeyword.class) != null) {
			} else if (acceptPart(NonWildcardTypeArguments.class) != null) {
				expectToken(Identifier.class);
				expectPart(Arguments.class);
			} else if (acceptToken(ThisKeyword.class) != null) {
			} else if (acceptToken(SuperKeyword.class) != null) {
				acceptPart(SuperSuffix.class);
			} else {
				abort();
			}
		} else if (acceptPart(InnerCreator.class) != null) {
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
