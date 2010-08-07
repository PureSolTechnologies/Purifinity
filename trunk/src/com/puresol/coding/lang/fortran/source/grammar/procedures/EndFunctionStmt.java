package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.EndFunctionKeyword;
import com.puresol.coding.lang.fortran.source.keywords.EndKeyword;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1232 end-function-stmt is END [ FUNCTION [ function-name ] ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class EndFunctionStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(EndFunctionKeyword.class) != null) {
			acceptPart(FunctionName.class);
		} else {
			expectToken(EndKeyword.class);
		}
		finish();
	}

}
