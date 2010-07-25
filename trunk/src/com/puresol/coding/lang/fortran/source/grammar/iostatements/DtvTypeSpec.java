package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.types.DerivedTypeSpec;
import com.puresol.coding.lang.fortran.source.keywords.ClassKeyword;
import com.puresol.coding.lang.fortran.source.keywords.TypeKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R921 dtv-type-spec is TYPE( derived-type-spec )
 * or CLASS( derived-type-spec )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DtvTypeSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(TypeKeyword.class) != null) {
		} else if (acceptToken(ClassKeyword.class) != null) {
		} else {
			abort();
		}
		expectToken(LParen.class);
		expectPart(DerivedTypeSpec.class);
		expectToken(RParen.class);
		finish();
	}

}
