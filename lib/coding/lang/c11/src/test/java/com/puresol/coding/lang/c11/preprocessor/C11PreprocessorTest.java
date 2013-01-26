package com.puresol.coding.lang.c11.preprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.uhura.preprocessor.PreprocessorException;
import com.puresol.uhura.source.CodeLocation;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;
import com.puresol.uhura.source.SourceFileLocation;

public class C11PreprocessorTest {

    @Test
    public void testInstance() {
	assertNotNull(new C11Preprocessor());
    }

    @Test
    public void testImmutabilityWithoutMacros() throws IOException,
	    PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	SourceCode sourceCode = new SourceFileLocation(new File(directory,
		"FileWithoutMacros.txt")).load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);
	assertEquals(sourceCode, preProcessedSourceCode);
	assertNotSame(sourceCode, preProcessedSourceCode);
    }

    @Test
    public void testSingleInclude() throws IOException, PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	SourceCode sourceCode = new SourceFileLocation(new File(directory,
		"SingleIncludeMacro.txt")).load();
	SourceCode includedSourceCode = new SourceFileLocation(new File(
		directory, "FileWithoutMacros.txt")).load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);
	assertEquals(includedSourceCode, preProcessedSourceCode);
    }

    @Test
    public void testMultipleIncludes() throws IOException,
	    PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	CodeLocation source = new SourceFileLocation(new File(directory,
		"MultipleIncludeMacros.txt"));
	SourceCode sourceCode = source.load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);

	SourceCode sourceWithoutMacros = new SourceFileLocation(new File(
		directory, "FileWithoutMacros.txt")).load();
	SourceCode sourceWithoutMacros2 = new SourceFileLocation(new File(
		directory, "FileWithoutMacros2.txt")).load();

	SourceCode expected = new SourceCode();
	expected.addSourceCode(sourceWithoutMacros);
	expected.addSourceCodeLine(new SourceCodeLine(source, 2, "\n"));
	expected.addSourceCode(sourceWithoutMacros);
	expected.addSourceCodeLine(new SourceCodeLine(source, 4,
		"// This is a non empty line\n"));
	expected.addSourceCode(sourceWithoutMacros2);
	expected.addSourceCodeLine(new SourceCodeLine(source, 6,
		"<end of file>"));

	assertEquals(expected, preProcessedSourceCode);
    }

    @Test
    public void testMultipleRecursiveIncludes() throws IOException,
	    PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	File sourceFile = new File(directory, "RecursiveIncludeMacros1.txt");
	SourceCode sourceCode = new SourceFileLocation(sourceFile).load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);

	SourceCode sourceWithoutMacros = new SourceFileLocation(new File(
		directory, "FileWithoutMacros.txt")).load();
	SourceCode sourceWithoutMacros2 = new SourceFileLocation(new File(
		directory, "FileWithoutMacros2.txt")).load();

	SourceCode expected = new SourceCode();
	File file = new File(directory, "RecursiveIncludeMacros3.txt");
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		file), 1, "<end of file>"));
	expected.addSourceCode(sourceWithoutMacros2);
	expected.addSourceCode(sourceWithoutMacros);

	assertEquals(expected, preProcessedSourceCode);
    }

    @Test
    public void testSimpleDefine() throws IOException, PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	File sourceFile = new File(directory, "SimpleDefineTest.txt");
	SourceCode sourceCode = new SourceFileLocation(sourceFile).load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);

	SourceCode expected = new SourceCode();
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 2, "1\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 4, "\"Hello, world!\"\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 6, "HELLO_WORLD\n"));
	assertEquals(expected, preProcessedSourceCode);
    }

    @Test
    public void testObjectLikeDefine() throws IOException,
	    PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	File sourceFile = new File(directory, "ObjectLikeDefineTest.txt");
	SourceCode sourceCode = new SourceFileLocation(sourceFile).load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);

	SourceCode expected = new SourceCode();
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 2,
		"Call the printf(\"This is a simple object macro!\"); macro now...\n"));
	assertEquals(expected, preProcessedSourceCode);
    }

    @Test
    public void testSimpleFunctionLikeDefine() throws IOException,
	    PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	File sourceFile = new File(directory,
		"SimpleFunctionLikeDefineTest.txt");
	SourceCode sourceCode = new SourceFileLocation(sourceFile).load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);

	SourceCode expected = new SourceCode();
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 2,
		"fprintf(stderr, \"%s\\\\n\", \"Error message!\");\n"));
	assertEquals(expected, preProcessedSourceCode);
    }

    @Test
    public void testFunctionLikeDefineTestWithMultipleLinesAndComment()
	    throws IOException, PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	File sourceFile = new File(directory,
		"FunctionLikeDefineTestWithMultipleLinesAndComment.txt");
	SourceCode sourceCode = new SourceFileLocation(sourceFile).load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);

	SourceCode expected = new SourceCode();
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 3, "\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 4, "    fprintf(stderr, \"%s\\\\n\", \n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 5, "// This is a comment\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 6, "\"Error message!\"\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 7, "/* This is another comment\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 8, "   with multiple lines! */\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 9, ");\n"));
	assertEquals(expected, preProcessedSourceCode);
    }

    @Test
    public void testSimpleIf() throws IOException, PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	File sourceFile = new File(directory, "SimpleIfTest.txt");
	SourceCode sourceCode = new SourceFileLocation(sourceFile).load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);

	SourceCode expected = new SourceCode();
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 4, "Else shown...\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 7, "Shown...\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 13, "Shown...\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 20, "Else shown...\n"));
	assertEquals(expected, preProcessedSourceCode);
    }

    @Test
    public void testComplexIf() throws IOException, PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/c11/preprocessor/files");
	File sourceFile = new File(directory, "ComplexIfTest.txt");
	SourceCode sourceCode = new SourceFileLocation(sourceFile).load();
	SourceCode preProcessedSourceCode = new C11Preprocessor()
		.process(sourceCode);

	SourceCode expected = new SourceCode();
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 5, "Calculation valid.\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 8, "Calculation valid.\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 12, "Calculation invalid.\n"));
	expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
		sourceFile), 15, "Calculation valid.\n"));
	assertEquals(expected, preProcessedSourceCode);
    }

}
