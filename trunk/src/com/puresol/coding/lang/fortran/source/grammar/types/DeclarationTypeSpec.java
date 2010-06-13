package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ClassKeyword;
import com.puresol.coding.lang.fortran.source.keywords.TypeKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.coding.lang.fortran.source.symbols.Star;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R403 declaration-type-spec is intrinsic-type-spec
 * or TYPE ( intrinsic-type-spec )
 * or TYPE ( derived-type-spec )
 * or CLASS ( derived-type-spec )
 * or CLASS ( * )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DeclarationTypeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(IntrinsicTypeSpec.class) != null) {
		} else if (acceptToken(TypeKeyword.class) != null) {
			expectToken(LParen.class);
			if (acceptPart(IntrinsicTypeSpec.class) != null) {
			} else if (acceptPart(DerivedTypeSpec.class) != null) {
			} else {
				abort();
			}
			expectToken(RParen.class);
		} else if (acceptToken(ClassKeyword.class) != null) {
			expectToken(LParen.class);
			if (acceptToken(Star.class) != null) {
			} else if (acceptPart(DerivedTypeSpec.class) != null) {
			} else {
				abort();
			}
			expectToken(RParen.class);
		} else {
			abort();
		}
		finish();
	}

}
