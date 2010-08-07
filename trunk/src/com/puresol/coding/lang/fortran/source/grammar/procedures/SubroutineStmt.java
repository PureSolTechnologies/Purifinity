package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.SubroutineKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1234 subroutine-stmt is [ prefix ] SUBROUTINE subroutine-name [ ( [ dummy-arg-list ] ) [ proc-language-binding-spec ] ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SubroutineStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(Prefix.class);
		expectToken(SubroutineKeyword.class);
		expectPart(SubroutineName.class);
		if (acceptToken(LParen.class) != null) {
			acceptPart(DummyArgList.class);
			expectToken(RParen.class);
			acceptPart(ProcLanguageBindingSpec.class);
		}
		finish();
	}

}
