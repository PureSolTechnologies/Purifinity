package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.LKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Slash;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1013 control-edit-desc is position-edit-desc
 * or [ r ] /
 * or :
 * or sign-edit-desc
 * or k P
 * or blank-interp-edit-desc
 * or round-edit-desc
 * or decimal-edit-desc
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ControlEditDesc extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(PositionEditDesc.class) != null) {
		} else if (acceptPart(R.class) != null) {
			expectToken(Slash.class);
		} else if (acceptToken(Slash.class) != null) {
		} else if (acceptToken(Colon.class) != null) {
		} else if (acceptPart(SignEditDesc.class) != null) {
		} else if (acceptPart(K.class) != null) {
			expectToken(PKeyword.class);
		} else if (acceptPart(BlankInterpEditDesc.class) != null) {
		} else if (acceptPart(RoundEditDesc.class) != null) {
		} else if (acceptPart(DecimalEditDesc.class) != null) {
		} else if (acceptToken(LKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
