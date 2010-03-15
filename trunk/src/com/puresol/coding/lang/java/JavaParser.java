package com.puresol.coding.lang.java;

import com.puresol.coding.analysis.AbstractSourceCodeParser;
import com.puresol.coding.analysis.CodeRangeType;
import com.puresol.coding.lang.Language;
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

	public JavaParser(TokenStream tokenStream) {
		setTokenStream(tokenStream);
		setStartPosition(0);
	}

	@Override
	public void scan() throws PartDoesNotMatchException, ParserException {
		TokenStream tokenStream = getTokenStream();
		addCodeRange(Language.JAVA, CodeRangeType.FILE, 0, tokenStream
				.getSize() - 1);

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
