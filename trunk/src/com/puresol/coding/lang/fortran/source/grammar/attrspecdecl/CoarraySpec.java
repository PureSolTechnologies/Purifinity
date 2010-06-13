package com.puresol.coding.lang.fortran.source.grammar.attrspecdecl;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PublicKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R509 coarray-spec is deferred-coshape-spec-list
 * or explicit-coshape-spec
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CoarraySpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(DeferredCoshapeSpecList.class) != null) {
		} else if (acceptToken(ExplicitCoshapeSpec.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
