package com.puresol.coding.lang.java;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.source.grammar.CompilationUnit;
import com.puresol.parser.EndOfTokenStream;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenPublicity;

/**
 * This class is the root parser for each file. In the Specifications this is
 * called CompilationUnit.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JavaParser extends CompilationUnit {

	private static final long serialVersionUID = -5271390812159304045L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		moveToNextProcessibleToken(0);
		super.scan();
		checkForFileEnd();
	}

	private void checkForFileEnd() throws PartDoesNotMatchException {
		for (int pos = getCurrentPosition() + 1; pos < getTokenStream()
				.getSize(); pos++) {
			if (getToken(pos).getPublicity() == TokenPublicity.VISIBLE) {
				if (!getToken(pos).getDefinition().equals(
						EndOfTokenStream.class)) {
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
