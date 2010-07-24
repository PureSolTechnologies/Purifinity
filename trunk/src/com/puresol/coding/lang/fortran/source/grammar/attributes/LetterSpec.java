package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Minus;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * letter-spec is letter [ { letter ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LetterSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		// NameLiteral is used here as 'letter'!
		expectToken(NameLiteral.class);
		while (acceptToken(Minus.class) != null) {
			expectToken(NameLiteral.class);
		}
		finish();
	}
}
