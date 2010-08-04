package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.CharLiteral;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1021 char-string-edit-desc is char-literal-constant
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CharStringEditDesc extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(CharLiteral.class);
		finish();
	}

}
