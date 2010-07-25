package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.WriteKeyword;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R911 write-stmt is WRITE ( io-control-spec-list ) [ output-item-list ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class WriteStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(WriteKeyword.class);
		expectToken(LParen.class);
		expectPart(IoControlSpecList.class);
		expectToken(RParen.class);
		acceptPart(OutputItemList.class);
		finish();
	}

}
