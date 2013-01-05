package com.puresol.coding.lang.c11.preprocessor;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.lang.reflect.Field;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.packrat.PackratParser;
import com.puresol.uhura.source.SourceCode;

public class C11PreprocessorTokenizerGrammarTest {

    private static C11Preprocessor preprocessor = null;

    @BeforeClass
    public static void initialize() throws IOException, GrammarException {
	preprocessor = new C11Preprocessor();
    }

    private void checkParser(String... lines) throws Exception {
	SourceCode sourceCode = SourceCode.fromStringArray(lines);

	Field preprocessorGrammar = C11Preprocessor.class
		.getDeclaredField("preprocessorGrammar");
	preprocessorGrammar.setAccessible(true);

	PackratParser parser = new PackratParser(
		(Grammar) preprocessorGrammar.get(null));
	ParserTree ast = parser.parse(sourceCode);

	assertNotNull(ast);
    }

    @Test
    public void testParsingEmptyFile() throws Exception {
	checkParser("");
    }

    @Test
    public void testParsingSimpleInclude1() throws Exception {
	checkParser("#include \"testfile.h\"\n");
    }

    @Test
    public void testParsingSimpleInclude2() throws Exception {
	checkParser("#include <testfile.h>\n");
    }

    @Test
    public void testError() throws Exception {
	checkParser("#error \"This is an error message!\"\n");
    }

    @Test
    public void testInclude() throws Exception {
	checkParser("#include <include.txt>\n");
    }

    @Test
    public void testLocalInclude() throws Exception {
	checkParser("#include \"include.txt\"\n");
    }

    @Test
    public void testElse() throws Exception {
	checkParser("#else\n");
    }

    @Test
    public void testEndIff() throws Exception {
	checkParser("#endif\n");
    }

    @Test
    public void testDefineMacroOnlyWithName() throws Exception {
	checkParser("#define NAME\n");
    }

    @Test
    public void testDefineObjectLikeMacroWithOneReplacement() throws Exception {
	checkParser("#define NAME replacement\n");
    }

    @Test
    public void testDefineObjectLikeMacroWithMultipleReplacements()
	    throws Exception {
	checkParser("#define NAME replacement1 replacement2 replacement3 replacement4\n");
    }

    @Test
    public void testDefineFunctionLikeMacroWithOneParameterAndWithOneReplacement()
	    throws Exception {
	checkParser("#define NAME(x) replacement1\n");
    }

    @Test
    public void testDefineFunctionLikeMacroWithOneParameterAndWithMultipleReplacements()
	    throws Exception {
	checkParser("#define NAME(x) replacement1 replacement2 replacement3 replacement4\n");
    }

    @Test
    public void testDefineFunctionLikeMacroWithMultipleParametersAndWithOneReplacement()
	    throws Exception {
	checkParser("#define NAME(x, y, z) replacement1\n");
    }

    @Test
    public void testDefineFunctionLikeMacroWithMultipleParametersAndWithMultipleReplacements()
	    throws Exception {
	checkParser("#define NAME(x, y, z) replacement1 replacement2 replacement3 replacement4\n");
    }

    @Test
    public void testDefineFunctionLikeMacroWithOptionalParametersOnly()
	    throws Exception {
	checkParser("#define NAME(...) replacement1\n");
    }

    @Test
    public void testDefineFunctionLikeMacroWithOneParameterAndOptionalParameters()
	    throws Exception {
	checkParser("#define NAME(x, ...) replacement1\n");
    }

    @Test
    public void testDefineFunctionLikeMacroWithMultipleParametersAndOptionalParameters()
	    throws Exception {
	checkParser("#define NAME(x, y, z, ...) replacement1\n");
    }

    @Test
    public void testDefineFunctionLikeMacroWithMultipleParametersOptionalParametersAndComplexReplacement()
	    throws Exception {
	checkParser("#define NAME(x, y, z, ...) fprintf(\"Hello x, y, z\"\n");
    }
}
