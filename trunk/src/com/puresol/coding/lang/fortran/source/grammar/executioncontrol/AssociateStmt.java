package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.AssociateKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Colon;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R803 associate-stmt is [ associate-construct-name : ] ASSOCIATE (association-list )
 * end-associate-stmt
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AssociateStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		if (acceptPart(AssociateConstructName.class) != null) {
			expectToken(Colon.class);
		}
		expectToken(AssociateKeyword.class);
		expectToken(LParen.class);
		expectPart(AssociationList.class);
		expectToken(RParen.class);
		finish();
	}
}
