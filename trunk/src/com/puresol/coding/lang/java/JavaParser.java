package com.puresol.coding.lang.java;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.source.grammar.CompilationUnit;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

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
		moveToNextProcessibleToken(getCurrentPosition());
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FILE;
	}
}
