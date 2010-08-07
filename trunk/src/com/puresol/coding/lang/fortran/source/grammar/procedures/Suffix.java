package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ResultKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1231 sufix is proc-language-binding-spec [ RESULT ( result-name ) ]
 * or RESULT ( result-name ) [ proc-language-binding-spec ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Suffix extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(ProcLanguageBindingSpec.class) != null) {
			if (acceptToken(ResultKeyword.class) != null) {
				expectToken(LParen.class);
				expectPart(ResultName.class);
				expectToken(RParen.class);
			}
		} else if (acceptToken(ResultKeyword.class) != null) {
			expectToken(LParen.class);
			expectPart(ResultName.class);
			expectToken(RParen.class);
			acceptPart(ProcLanguageBindingSpec.class);
		} else {
			abort();
		}
		finish();
	}

}
