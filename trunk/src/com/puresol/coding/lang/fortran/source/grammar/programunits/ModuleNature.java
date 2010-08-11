package com.puresol.coding.lang.fortran.source.grammar.programunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.IntrinsicKeyword;
import com.puresol.coding.lang.fortran.source.keywords.NonKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1110 module-nature is INTRINSIC
 * or NON INTRINSIC
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ModuleNature extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(NonKeyword.class) != null) {
			expectToken(IntrinsicKeyword.class);
		} else if (acceptToken(IntrinsicKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
