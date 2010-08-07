package com.puresol.coding.lang.fortran.source.grammar.procedures;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.FunctionKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R1228 function-stmt is [ prefix ] FUNCTION function-name ( [ dummy-arg-name-list ] ) [ sufix ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FunctionStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		acceptPart(Prefix.class);
		expectToken(FunctionKeyword.class);
		expectPart(FunctionName.class);
		expectToken(LParen.class);
		acceptPart(DummyArgNameList.class);
		expectToken(RParen.class);
		acceptPart(Suffix.class);
		finish();
	}

}
