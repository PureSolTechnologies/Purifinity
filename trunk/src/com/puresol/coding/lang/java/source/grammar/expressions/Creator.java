package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.NonWildcardTypeArguments;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.ClassOrInterfaceType;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * creator 
 *     :   'new' nonWildcardTypeArguments classOrInterfaceType classCreatorRest
 *     |   'new' classOrInterfaceType classCreatorRest
 *     |   arrayCreator
 *     ;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Creator extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ArrayCreator.class) != null) {
		} else if (acceptToken(NewKeyword.class) != null) {
			acceptPart(NonWildcardTypeArguments.class);
			expectPart(ClassOrInterfaceType.class);
			expectPart(ClassCreatorRest.class);
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
