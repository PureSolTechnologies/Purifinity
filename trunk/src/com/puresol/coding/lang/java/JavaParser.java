package com.puresol.coding.lang.java;

import javax.i18n4j.Translator;

import com.puresol.coding.AbstractSourceCodeParser;
import com.puresol.coding.CodeRange;
import com.puresol.coding.CodeRangeType;
import com.puresol.coding.lang.java.source.parts.ClassDeclaration;
import com.puresol.coding.lang.java.source.parts.Import;
import com.puresol.coding.lang.java.source.parts.PackageDeclaration;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class JavaParser extends AbstractSourceCodeParser {

    private static final Translator translator =
	    Translator.getTranslator(JavaParser.class);

    public JavaParser(TokenStream tokenStream) {
	super(tokenStream, 0);
    }

    @Override
    public void scan() throws PartDoesNotMatchException {
	TokenStream tokenStream = getTokenStream();
	addCodeRange(new CodeRange(tokenStream.getName(),
		CodeRangeType.FILE, translator.i18n("File"), tokenStream,
		0, tokenStream.getSize() - 1));

	moveForward(0);
	processPart(PackageDeclaration.class);
	while (isPart(Import.class)) {
	    processPart(Import.class);
	}
	processPart(ClassDeclaration.class);
    }
}
