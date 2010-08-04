package com.puresol.coding.lang.fortran.source.grammar.ioediting;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.RCKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RDKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RNKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RPKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RUKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RZKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * 1019 round-edit-desc is RU
 * or RD
 * or RZ
 * or RN
 * or RC
 * or RP
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RoundEditDesc extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(RUKeyword.class) != null) {
		} else if (acceptToken(RDKeyword.class) != null) {
		} else if (acceptToken(RZKeyword.class) != null) {
		} else if (acceptToken(RNKeyword.class) != null) {
		} else if (acceptToken(RCKeyword.class) != null) {
		} else if (acceptToken(RPKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
