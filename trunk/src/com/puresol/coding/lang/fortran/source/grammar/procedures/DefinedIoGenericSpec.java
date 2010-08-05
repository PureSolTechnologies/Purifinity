package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.FormattedKeyword;
import com.puresol.coding.lang.fortran.source.keywords.ReadKeyword;
import com.puresol.coding.lang.fortran.source.keywords.UnformattedKeyword;
import com.puresol.coding.lang.fortran.source.keywords.WriteKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1208 defined-io-generic-spec is READ (FORMATTED)
 * or READ (UNFORMATTED)
 * or WRITE (FORMATTED)
 * or WRITE (UNFORMATTED)
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DefinedIoGenericSpec extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptToken(ReadKeyword.class) != null) {
		} else if (acceptToken(WriteKeyword.class) != null) {
		} else {
			abort();
		}
		expectToken(LParen.class);
		if (acceptToken(FormattedKeyword.class) != null) {
		} else if (acceptToken(UnformattedKeyword.class) != null) {
		} else {
			abort();
		}
		expectToken(RParen.class);
		finish();
	}

}
