package com.puresol.coding.lang.fortran.source.grammar.executioncontrol;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.AbstractFortranParser;
import com.puresol.coding.lang.fortran.source.keywords.SyncImagesKeyword;
import com.puresol.coding.lang.fortran.source.symbols.Comma;
import com.puresol.coding.lang.fortran.source.symbols.LParen;
import com.puresol.coding.lang.fortran.source.symbols.RParen;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * <pre>
 * R860 sync-images-stmt is SYNC IMAGES ( image-set [ , sync-stat-list ] )
 * </pre>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SyncImagesStmt extends AbstractFortranParser {

	private static final long serialVersionUID = 2177336093526924891L;

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FRAGMENT;
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		expectToken(SyncImagesKeyword.class);
		expectToken(LParen.class);
		expectPart(ImageSet.class);
		if (acceptToken(Comma.class) != null) {
			expectPart(SyncStatList.class);
		}
		expectToken(RParen.class);
		finish();
	}
}
