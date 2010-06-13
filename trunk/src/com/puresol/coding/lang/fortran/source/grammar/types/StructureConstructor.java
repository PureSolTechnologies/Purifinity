package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R455 structure-constructor is derived-type-spec ( [ component-spec-list ] )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class StructureConstructor extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(DerivedTypeSpec.class);
		if (acceptToken(LParen.class) != null) {
			acceptPart(ComponentSpecList.class);
			expectToken(RParen.class);
		}
		finish();
	}

}
