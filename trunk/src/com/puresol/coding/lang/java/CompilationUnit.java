package com.puresol.coding.lang.java;

import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.java.source.parts.packages.ImportDeclarations;
import com.puresol.coding.lang.java.source.parts.packages.PackageDeclaration;
import com.puresol.coding.lang.java.source.parts.packages.TypeDeclarations;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;

/**
 * This class is the root parser for each file. In the Specifications this is
 * called CompilationUnit.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CompilationUnit extends AbstractJavaParser {

	private static final long serialVersionUID = -5271390812159304045L;

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		try {
			moveToNextVisible(0);
		} catch (EndOfTokenStreamException e) {
			// this may happen if there is an empty file...
			return;
		}
		acceptPart(PackageDeclaration.class);
		acceptPart(ImportDeclarations.class);
		acceptPart(TypeDeclarations.class);
		if (getCurrentPosition() < getTokenStream().getSize()) {
			// we are not at end; therefore, we stopped unexpected...
			throw new PartDoesNotMatchException(this);
		}
		finish(getFile().getName());
	}

	@Override
	public CodeRangeType getCodeRangeType() {
		return CodeRangeType.FILE;
	}
}
