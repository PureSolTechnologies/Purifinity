package com.puresol.coding.lang.cpp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.uhura.lexer.SourceCode;
import com.puresol.uhura.lexer.SourceCodeLine;
import com.puresol.uhura.preprocessor.PreprocessorException;

public class CPreprocessorTest {

    @Test
    public void testInstantiation() {
	assertNotNull(new CPreprocessor());
    }

    @Test
    public void testImmutabilityWithoutMacros() throws IOException,
	    PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/cpp/files");
	SourceCode sourceCode = SourceCode.read(new File(directory,
		"FileWithoutMacros.txt"));
	SourceCode preProcessedSourceCode = new CPreprocessor()
		.process(sourceCode);
	assertEquals(sourceCode, preProcessedSourceCode);
	assertNotSame(sourceCode, preProcessedSourceCode);
    }

    @Test
    public void testSingleInclude() throws IOException, PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/cpp/files");
	SourceCode sourceCode = SourceCode.read(new File(directory,
		"SingleIncludeMacro.txt"));
	SourceCode includedSourceCode = SourceCode.read(new File(directory,
		"FileWithoutMacros.txt"));
	SourceCode preProcessedSourceCode = new CPreprocessor()
		.process(sourceCode);
	assertEquals(includedSourceCode, preProcessedSourceCode);
    }

    @Test
    public void testMultipleIncludes() throws IOException,
	    PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/cpp/files");
	File sourceFile = new File(directory, "MultipleIncludeMacros.txt");
	SourceCode sourceCode = SourceCode.read(sourceFile);
	SourceCode preProcessedSourceCode = new CPreprocessor()
		.process(sourceCode);

	SourceCode sourceWithoutMacros = SourceCode.read(new File(directory,
		"FileWithoutMacros.txt"));
	SourceCode sourceWithoutMacros2 = SourceCode.read(new File(directory,
		"FileWithoutMacros2.txt"));

	SourceCode expected = new SourceCode();
	expected.addSourceCode(sourceWithoutMacros);
	expected.addSourceCodeLine(new SourceCodeLine(sourceFile, 2, "\n"));
	expected.addSourceCode(sourceWithoutMacros);
	expected.addSourceCodeLine(new SourceCodeLine(sourceFile, 4,
		"// This is a non empty line\n"));
	expected.addSourceCode(sourceWithoutMacros2);
	expected.addSourceCodeLine(new SourceCodeLine(sourceFile, 6,
		"<end of file>"));

	assertEquals(expected, preProcessedSourceCode);
    }

    @Test
    public void testMultipleRecursiveIncludes() throws IOException,
	    PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/cpp/files");
	File sourceFile = new File(directory, "RecursiveIncludeMacros1.txt");
	SourceCode sourceCode = SourceCode.read(sourceFile);
	SourceCode preProcessedSourceCode = new CPreprocessor()
		.process(sourceCode);

	SourceCode sourceWithoutMacros = SourceCode.read(new File(directory,
		"FileWithoutMacros.txt"));
	SourceCode sourceWithoutMacros2 = SourceCode.read(new File(directory,
		"FileWithoutMacros2.txt"));

	SourceCode expected = new SourceCode();
	expected.addSourceCodeLine(new SourceCodeLine(new File(directory,
		"RecursiveIncludeMacros3.txt"), 1, "<end of file>"));
	expected.addSourceCode(sourceWithoutMacros2);
	expected.addSourceCode(sourceWithoutMacros);

	assertEquals(expected, preProcessedSourceCode);
    }
}
