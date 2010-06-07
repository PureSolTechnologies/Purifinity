package com.puresol.coding.lang.java.source.grammar.expressions;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.AbstractJavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.NonWildcardTypeArguments;
import com.puresol.coding.lang.java.source.grammar.types_values_variables.TypeArguments;
import com.puresol.coding.lang.java.source.keywords.NewKeyword;
import com.puresol.coding.lang.java.source.literals.Identifier;
import com.puresol.coding.lang.java.source.symbols.Dot;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * innerCreator  
 *     :   '.' 'new'
 *         (nonWildcardTypeArguments
 *         )?
 *         IDENTIFIER
 *         (typeArguments
 *         )?
 *         classCreatorRest
 *     ;
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InnerCreator extends AbstractJavaParser {

	private static final long serialVersionUID = 6464754895556318548L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(Dot.class);
		expectToken(NewKeyword.class);
		acceptPart(NonWildcardTypeArguments.class);
		expectToken(Identifier.class);
		acceptPart(TypeArguments.class);
		expectPart(ClassCreatorRest.class);
		finish();
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

}
