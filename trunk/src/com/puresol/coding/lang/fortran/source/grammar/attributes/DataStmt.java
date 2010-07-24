package com.puresol.coding.lang.fortran.source.grammar.attributes;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.DataKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R534 data-stmt is DATA data-stmt-set [ [ , ] data-stmt-set ] ...
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DataStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(DataKeyword.class);
		expectPart(DataStmtSet.class);
		while (true) {
			if (acceptToken(Comma.class) != null) {
				expectPart(DataStmtSet.class);
			} else {
				if (acceptPart(DataStmtSet.class) == null) {
					break;
				}
			}
		}
		finish();
	}

}
