package com.puresol.coding.lang.java;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.lang.java.source.coderanges.JavaFile;
import com.puresol.coding.lang.java.source.parts.ClassDeclaration;
import com.puresol.coding.lang.java.source.parts.EnumDeclaration;
import com.puresol.coding.lang.java.source.parts.Import;
import com.puresol.coding.lang.java.source.parts.InterfaceDeclaration;
import com.puresol.coding.lang.java.source.parts.PackageDeclaration;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class JavaParser extends AbstractSourceCodeParser {

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		TokenStream tokenStream = getTokenStream();
		addCodeRange(new JavaFile(tokenStream.getFile().getName(), tokenStream,
				0, tokenStream.getSize() - 1));

		try {
			moveForward(0);
		} catch (EndOfTokenStreamException e) {
			// this may happen if there is an empty file...
			return;
		}
		processPart(PackageDeclaration.class);
		while (isPart(Import.class)) {
			processPart(Import.class);
		}
		if (processPartIfPossible(ClassDeclaration.class)) {
		} else if (processPartIfPossible(InterfaceDeclaration.class)) {
		} else if (processPartIfPossible(EnumDeclaration.class)) {
		} else {
			throw new PartDoesNotMatchException(this);
		}
	}
}
