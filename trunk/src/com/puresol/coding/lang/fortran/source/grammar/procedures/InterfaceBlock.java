package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.AbstractKeyword;
import com.puresol.coding.lang.fortran.source.keywords.InterfaceKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1203 interface-stmt is INTERFACE [ generic-spec ]
 * or ABSTRACT INTERFACE
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InterfaceBlock extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(AbstractKeyword.class) != null) {
			expectToken(InterfaceKeyword.class);
		} else {
			expectToken(InterfaceKeyword.class);
			acceptPart(GenericSpec.class);
		}
		finish();
	}

}
