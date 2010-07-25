package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.expressions.DefaultCharExpr;
import com.puresol.coding.lang.fortran.source.grammar.lexical.Label;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R915 format is default-char-expr
 * or label
 * or *
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Format extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DefaultCharExpr.class) != null) {
		} else if (acceptPart(Label.class) != null) {
		} else if (acceptToken(Star.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
