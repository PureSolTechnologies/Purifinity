package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1004 format-item is [ r ] data-edit-desc
 * or control-edit-desc
 * or char-string-edit-desc
 * or [ r ] ( format-items )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FormatItem extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(R.class) != null) {
			if (acceptPart(DataEditDesc.class) != null) {
			} else {
				expectToken(LParen.class);
				expectPart(FormatItem.class);
				expectToken(RParen.class);
			}
		} else if (acceptPart(DataEditDesc.class) != null) {
		} else if (acceptPart(ControlEditDesc.class) != null) {
		} else if (acceptPart(CharStringEditDesc.class) != null) {
		} else {
			expectToken(LParen.class);
			expectPart(FormatItem.class);
			expectToken(RParen.class);
		}
		finish();
	}
}
