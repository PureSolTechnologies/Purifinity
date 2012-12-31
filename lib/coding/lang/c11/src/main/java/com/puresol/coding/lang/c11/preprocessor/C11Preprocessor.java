package com.puresol.coding.lang.c11.preprocessor;

import com.puresol.coding.lang.c11.C11;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.packrat.PackratParser;
import com.puresol.uhura.preprocessor.Preprocessor;
import com.puresol.uhura.preprocessor.PreprocessorException;
import com.puresol.uhura.source.SourceCode;

public class C11Preprocessor implements Preprocessor {

    private static final C11 c11 = C11.getInstance();
    private static final Grammar preprocessorGrammar;
    static {
	try {
	    preprocessorGrammar = c11.getGrammar()
		    .createWithNewStartProduction("preprocessing-file");
	} catch (GrammarException e) {
	    throw new RuntimeException(
		    "Could not initialize preprocessor grammar!", e);
	}
    }

    @Override
    public SourceCode process(SourceCode sourceCode)
	    throws PreprocessorException {
	try {
	    PackratParser parser = new PackratParser(preprocessorGrammar);
	    ParserTree ast = parser.parse(sourceCode);
	    return sourceCode;
	} catch (ParserException e) {
	    throw new PreprocessorException(
		    "Could not preprocess source code!", e);
	}
    }

}
