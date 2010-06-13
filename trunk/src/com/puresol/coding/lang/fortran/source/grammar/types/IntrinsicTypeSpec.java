package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.CharacterKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ComplexKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DoublePrecisionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IntegerKeyword;
import com.puresol.coding.lang.fortran.source.keywords.LogicalKeyword;
import com.puresol.coding.lang.fortran.source.keywords.RealKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R404 intrinsic-type-spec is INTEGER [ kind-selector ]
 * or REAL [ kind-selector ]
 * or DOUBLE PRECISION
 * or COMPLEX [ kind-selector ]
 * or CHARACTER [ char-selector ]
 * or LOGICAL [ kind-selector ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IntrinsicTypeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(IntegerKeyword.class) != null) {
			acceptPart(KindSelector.class);
		} else if (acceptToken(RealKeyword.class) != null) {
			acceptPart(KindSelector.class);
		} else if (acceptToken(DoublePrecisionKeyword.class) != null) {
		} else if (acceptToken(ComplexKeyword.class) != null) {
			acceptPart(KindSelector.class);
		} else if (acceptToken(CharacterKeyword.class) != null) {
			acceptPart(KindSelector.class);
		} else if (acceptToken(LogicalKeyword.class) != null) {
			acceptPart(KindSelector.class);
		} else {
			abort();
		}
		finish();
	}

}
