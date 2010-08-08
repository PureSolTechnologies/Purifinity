package com.puresol.coding.lang.fortran;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.fortran.source.grammar.highlevel.Program;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.tokens.EndOfTokenStreamToken;
import com.puresol.parser.tokens.EndOfTokenStreamException;
import com.puresol.parser.tokens.TokenPublicity;

/**
 * This class is the root parser for each file. In the Specifications this is
 * called CompilationUnit.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FortranParser extends Program {

	private static final long serialVersionUID = -5271390812159304045L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		try {
			moveToNextVisibleToken(0);
			super.scan();
			checkForFileEnd();
		} catch (EndOfTokenStreamException e) {
			throw new PartDoesNotMatchException(this);
		}
	}

	private void checkForFileEnd() throws PartDoesNotMatchException {
		for (int pos = getCurrentPosition() + 1; pos < getTokenStream()
				.getSize(); pos++) {
			if (getToken(pos).getPublicity() == TokenPublicity.VISIBLE) {
				if (!getToken(pos).getDefinition().equals(
						EndOfTokenStreamToken.class)) {
					throw new PartDoesNotMatchException(this);
				}
			}
		}
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FILE;
	}
}
