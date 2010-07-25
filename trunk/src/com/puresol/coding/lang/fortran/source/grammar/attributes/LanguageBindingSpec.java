package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.ScalarDefaultCharConstantExpr;
import com.puresol.coding.lang.fortran.source.keywords.BindKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NameKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * 508 language-binding-spec is BIND (C [, NAME = scalar-default-char-constant-expr ])
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LanguageBindingSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(BindKeyword.class);
		expectToken(LParen.class);
		expectToken(CKeyword.class);
		if (acceptToken(Comma.class) != null) {
			expectToken(NameKeyword.class);
			expectToken(Equals.class);
			expectPart(ScalarDefaultCharConstantExpr.class);
		}
		finish();
	}

}
