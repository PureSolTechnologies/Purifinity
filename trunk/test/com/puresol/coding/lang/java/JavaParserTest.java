/**
 * Test //
 */
package com.puresol.coding.lang.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.puresol.coding.SourceCodeLexer;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.lang.java.JavaParser;
import com.puresol.coding.lang.java.source.grammar.classes.ClassDeclaration;
import com.puresol.parser.DefaultPreConditioner;
import com.puresol.parser.LexerException;
import com.puresol.parser.NoMatchingTokenDefinitionFound;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.di.DIClassBuilder;
import com.puresol.utils.di.Injection;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JavaParserTest extends TestCase {

    public int testInt;

    @Test
    public void test() {
	// Logger.getRootLogger().setLevel(Level.DEBUG);
	JavaParser parser = null;
	try {
	    DefaultPreConditioner conditioner = new DefaultPreConditioner(
		    new File("test"), FileUtilities
			    .classToRelativePackagePath(JavaParserTest.class));
	    TokenStream tokenStream = conditioner.getTokenStream();
	    SourceCodeLexer lexer = new SourceCodeLexer(Java.getInstance(),
		    tokenStream);
	    TokenStream tokenStream2 = lexer.getTokenStream();
	    parser = DIClassBuilder.forInjections(
		    Injection.named("TokenStream", tokenStream2))
		    .createInstance(JavaParser.class);
	    parser.scan();
	    for (CodeRange codeRange : parser.getChildCodeRanges()) {
		System.out.println(codeRange.toString());
	    }
	    List<ClassDeclaration> classes = parser
		    .getChildCodeRanges(ClassDeclaration.class);
	    Assert.assertEquals(1, classes.size());
	    List<ClassDeclaration> classElements = parser
		    .getChildCodeRanges(ClassDeclaration.class);
	    Assert.assertEquals(1, classElements.size());
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (IOException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (NoMatchingTokenDefinitionFound e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (PartDoesNotMatchException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (ParserException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (LexerException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	} catch (ClassInstantiationException e) {
	    e.printStackTrace();
	    Assert.fail("No exception was expected!");
	}
    }
}
