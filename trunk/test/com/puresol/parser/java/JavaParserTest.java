package com.puresol.parser.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.Lexer;
import com.puresol.parser.NoMatchingTokenDefintionFound;
import com.puresol.parser.Parser;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.parser.java.partdef.JavaFile;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaParserTest extends TestCase {

    @Test
    public void test() {
	try {
	    DefaultPreConditioner conditioner =
		    new DefaultPreConditioner(
			    new File(
				    "test/com/puresol/parser/java/JavaLexerTest.java"));
	    TokenStream tokenStream = conditioner.getTokenStream();
	    Lexer lexer = new Lexer(tokenStream);
	    lexer.addDefinitions(JavaKeywords.class);
	    lexer.addDefinitions(JavaSymbols.class);
	    TokenStream tokenStream2 = lexer.getTokenStream();
	    Parser parser = new Parser(tokenStream2);
	    parser.parse(JavaFile.class);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (IOException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (NoMatchingTokenDefintionFound e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (PartDoesNotMatchException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }
}
