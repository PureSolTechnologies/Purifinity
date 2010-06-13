package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.literals.NameLiteral;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.RSquareBracket;
import com.puresol.coding.lang.fortran.source.symbols.Slash;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R402 type-spec is intrinsic-type-spec
 * or derived-type-spec
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(IntrinsicTypeSpec.class) != null) {
		} else if (acceptPart(DerivedTypeSpec.class) != null) {
		} else {
			abort();
		}
		finish();
	}

}
