package com.puresol.coding.lang.fortran.source.grammar.types.derivedtypes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R439 component-array-spec is explicit-shape-spec-list
 * or deferred-shape-spec-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ComponentArraySpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ExplicitShapeSpecList.class) != null) {
		} else if (acceptPart(DeferredShapeSpecList.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
