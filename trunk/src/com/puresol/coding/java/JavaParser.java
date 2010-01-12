package com.puresol.coding.java;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.java.source.parts.ClassDeclaration;
import com.puresol.coding.java.source.parts.Import;
import com.puresol.coding.java.source.parts.PackageDeclaration;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class JavaParser extends AbstractSourceCodeParser {

	public JavaParser(TokenStream tokenStream) {
		super(tokenStream, 0);
	}

	@Override
	public void scan() throws PartDoesNotMatchException {
		moveForward(0);
		processPart(PackageDeclaration.class);
		while (isPart(Import.class)) {
			processPart(Import.class);
		}
		processPart(ClassDeclaration.class);
	}
}
