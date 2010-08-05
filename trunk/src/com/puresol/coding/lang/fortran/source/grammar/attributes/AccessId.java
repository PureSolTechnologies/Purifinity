package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.procedures.GenericSpec;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R525 access-id is use-name
 * or generic-spec
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AccessId extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(UseName.class) != null) {
		} else if (acceptPart(GenericSpec.class) != null) {
		} else {
			abort();
		}

		finish();
	}

}
