package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.ExplicitShapeSpecList;
import com.puresol.coding.lang.fortran.source.keywords.DimensionKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R515 array-spec is explicit-shape-spec-list
 * or assumed-shape-spec-list
 * or deferred-shape-spec-list
 * or assumed-size-spec
 * or implied-shape-spec-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ArraySpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ExplicitShapeSpecList.class) != null) {
		} else if (acceptPart(AssumedShapeSpecList.class) != null) {
		} else if (acceptPart(DeferredShapeSpecList.class) != null) {
		} else if (acceptPart(AssumedSizeSpec.class) != null) {
		} else if (acceptPart(ImpliedShapeSpecList.class) != null) {
		}
		finish();
	}
}
