package com.puresol.coding.lang.fortran.source.grammar.attrspecdecl.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.PrivateKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PublicKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R507 access-spec is PUBLIC
 * or PRIVATE
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AccessSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(PublicKeyword.class) != null) {
		} else if (acceptToken(PrivateKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
