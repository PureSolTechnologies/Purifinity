package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.attributes.AccessSpec;
import com.puresol.coding.lang.fortran.source.grammar.attributes.CoarraySpec;
import com.puresol.coding.lang.fortran.source.keywords.AllocatableKeyword;
import com.puresol.coding.lang.fortran.source.keywords.CodimensionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ContiguousKeyword;
import com.puresol.coding.lang.fortran.source.keywords.DimensionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.PointerKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.LSquareBracket;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.RSquareBracket;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R437 component-attr-spec is access-spec
 * or ALLOCATABLE
 * or CODIMENSION lbracket coarray-spec rbracket
 * or CONTIGUOUS
 * or DIMENSION ( component-array-spec )
 * or POINTER
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ComponentAttrSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(AccessSpec.class) != null) {
		} else if (acceptToken(AllocatableKeyword.class) != null) {
		} else if (acceptToken(CodimensionKeyword.class) != null) {
			acceptToken(LSquareBracket.class);
			acceptPart(CoarraySpec.class);
			acceptToken(RSquareBracket.class);
		} else if (acceptToken(ContiguousKeyword.class) != null) {
		} else if (acceptToken(DimensionKeyword.class) != null) {
			acceptToken(LParen.class);
			acceptPart(ComponentArraySpec.class);
			acceptToken(RParen.class);
		} else if (acceptToken(PointerKeyword.class) != null) {
		} else {
			abort();
		}
		finish();
	}
}
