package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.grammar.dataobjects.ScalarIntVariable;
import com.puresol.coding.lang.fortran.source.keywords.InquireKeyword;
import com.puresol.coding.lang.fortran.source.keywords.IoLengthKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Equals;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R930 inquire-stmt is INQUIRE ( inquire-spec-list )
 * or INQUIRE ( IOLENGTH = scalar-int-variable )
 * output-item-list
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class InquireStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(InquireKeyword.class);
		expectToken(LParen.class);
		if (acceptPart(InquireSpecList.class) != null) {

		} else {
			expectToken(IoLengthKeyword.class);
			expectToken(Equals.class);
			expectPart(ScalarIntVariable.class);
		}
		expectToken(RParen.class);
		finish();
	}

}
