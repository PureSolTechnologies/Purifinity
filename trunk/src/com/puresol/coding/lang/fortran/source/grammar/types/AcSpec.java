package com.puresol.coding.lang.fortran.source.grammar.types;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R469 ac-spec is type-spec ::
 * or [type-spec ::] ac-value-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AcSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(TypeSpec.class) != null) {
			expectToken(Colon.class);
			expectToken(Colon.class);
			acceptPart(AcValueList.class);
		} else {
			expectPart(AcValueList.class);
		}
		finish();
	}

}
