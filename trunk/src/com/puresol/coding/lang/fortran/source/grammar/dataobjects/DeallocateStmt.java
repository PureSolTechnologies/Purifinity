package com.puresol.coding.lang.fortran.source.grammar.dataobjects;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.DeallocateKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R640 deallocate-stmt is DEALLOCATE ( allocate-object-list [ , dealloc-opt-list ] )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DeallocateStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(DeallocateKeyword.class);
		expectToken(LParen.class);
		expectPart(AllocateObjectList.class);
		if (acceptToken(Comma.class) != null) {
			expectPart(DeallocOptList.class);
		}
		expectToken(RParen.class);
		finish();
	}
}
