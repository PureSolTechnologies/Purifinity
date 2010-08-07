package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R428 private-or-sequence is private-components-stmt
 * or sequence-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PrivateOrSequence extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(PrivateComponentsStmt.class) != null) {
		} else if (acceptPart(SequenceStmt.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
