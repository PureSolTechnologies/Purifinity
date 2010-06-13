package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * TypeAttrSpec (, TypeAttrSpec)*
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TypeAttrSpecList extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectPart(TypeAttrSpec.class);
		while (acceptToken(Comma.class) != null) {
			expectPart(TypeAttrSpec.class);
		}
		finish();
	}

}
