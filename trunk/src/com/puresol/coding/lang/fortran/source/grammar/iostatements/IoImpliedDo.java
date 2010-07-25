package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R918 io-implied-do is ( io-implied-do-object-list , io-implied-do-control )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class IoImpliedDo extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(LParen.class);
		expectPart(IoImpliedDoObjectList.class);
		expectToken(Comma.class);
		expectPart(IoImpliedDoControl.class);
		expectToken(RParen.class);
		finish();
	}

}
