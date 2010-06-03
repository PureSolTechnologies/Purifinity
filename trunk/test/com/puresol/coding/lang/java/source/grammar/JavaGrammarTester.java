package com.puresol.coding.lang.java.source.grammar;

import com.puresol.coding.SourceCodeLexer;
import com.puresol.coding.lang.java.Java;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.di.DIClassBuilder;
import com.puresol.utils.di.Injection;

public class JavaGrammarTester {

    public static boolean valid(String string,
	    Class<? extends Parser> grammarPart) {
	return new JavaGrammarTester(string, grammarPart).valid();
    }

    public static boolean invalid(String string,
	    Class<? extends Parser> grammarPart) {
	return new JavaGrammarTester(string, grammarPart).invalid();
    }

    private final String string;
    private final Class<? extends Parser> grammarPart;

    private JavaGrammarTester(String string, Class<? extends Parser> grammarPart) {
	this.string = string;
	this.grammarPart = grammarPart;
    }

    private boolean valid() {
	try {
	    SourceCodeLexer lexer = new SourceCodeLexer(Java.getInstance(),
		    getClass().getSimpleName(), string);
	    DIClassBuilder classBuilder = DIClassBuilder
		    .forInjections(Injection.named("TokenStream", lexer
			    .getTokenStream()));
	    Parser parser = classBuilder.createInstance(grammarPart);
	    parser.scan();
	    return parser.getText().equals(string);
	} catch (LexerException e) {
	    e.printStackTrace();
	} catch (PartDoesNotMatchException e) {
	    e.printStackTrace();
	} catch (ParserException e) {
	    e.printStackTrace();
	} catch (NoMatchingTokenDefinitionFound e) {
	    e.printStackTrace();
	} catch (ClassInstantiationException e) {
	    e.printStackTrace();
	}
	return false;
    }

    private boolean invalid() {
	try {
	    SourceCodeLexer lexer = new SourceCodeLexer(Java.getInstance(),
		    getClass().getSimpleName(), string);
	    DIClassBuilder classBuilder = DIClassBuilder
		    .forInjections(Injection.named("TokenStream", lexer
			    .getTokenStream()));
	    Parser parser = classBuilder.createInstance(grammarPart);
	    parser.scan();
	    return !parser.getText().equals(string);
	} catch (LexerException e) {
	} catch (PartDoesNotMatchException e) {
	} catch (ParserException e) {
	} catch (NoMatchingTokenDefinitionFound e) {
	} catch (ClassInstantiationException e) {
	}
	return true; // was expected!
    }
}
