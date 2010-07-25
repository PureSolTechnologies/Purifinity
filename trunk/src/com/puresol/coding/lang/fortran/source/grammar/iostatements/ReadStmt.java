package com.puresol.coding.lang.fortran.source.grammar.iostatements;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.ReadKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R910 read-stmt is READ ( io-control-spec-list ) [ input-item-list ]
 * or READ format [ , input-item-list ]
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ReadStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(ReadKeyword.class);
		if (acceptToken(LParen.class) != null) {
			expectPart(IoControlSpecList.class);
			expectToken(RParen.class);
			acceptPart(InputItemList.class);
		} else {
			expectPart(Format.class);
			if (acceptToken(Comma.class) != null) {
				expectPart(InputItemList.class);
			}
		}
		finish();
	}

}
