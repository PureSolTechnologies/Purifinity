package com.puresol.coding.lang.fortran.source.grammar.programunits;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.procedures.GenericSpec;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1112 only is generic-spec
 * or only-use-name
 * or rename
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Only extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(GenericSpec.class) != null) {
		} else if (acceptPart(OnlyUseName.class) != null) {
		} else if (acceptPart(Rename.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
