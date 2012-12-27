package com.puresol.coding.lang.cpp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.uhura.preprocessor.PreprocessorException;
import com.puresol.uhura.source.FileSource;
import com.puresol.uhura.source.Source;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;

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
	SourceCode sourceCode = new FileSource(new File(directory,
		"FileWithoutMacros.txt")).load();
	SourceCode preProcessedSourceCode = new CPreprocessor()
		.process(sourceCode);
	assertEquals(sourceCode, preProcessedSourceCode);
	assertNotSame(sourceCode, preProcessedSourceCode);
    }

    @Test
    public void testSingleInclude() throws IOException, PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/cpp/files");
	SourceCode sourceCode = new FileSource(new File(directory,
		"SingleIncludeMacro.txt")).load();
	SourceCode includedSourceCode = new FileSource(new File(directory,
		"FileWithoutMacros.txt")).load();
	SourceCode preProcessedSourceCode = new CPreprocessor()
		.process(sourceCode);
	assertEquals(includedSourceCode, preProcessedSourceCode);
    }

    @Test
    public void testMultipleIncludes() throws IOException,
	    PreprocessorException {
	File directory = new File(
		"src/test/resources/com/puresol/coding/lang/cpp/files");
	Source source = new FileSource(new File(directory,
		"MultipleIncludeMacros.txt"));
	SourceCode sourceCode = source.load();
	SourceCode preProcessedSourceCode = new CPreprocessor()
		.process(sourceCode);

	SourceCode sourceWithoutMacros = new FileSource(new File(directory,
		"FileWithoutMacros.txt")).load();
	SourceCode sourceWithoutMacros2 = new FileSource(new File(directory,
		"FileWithoutMacros2.txt")).load();

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
		"src/test/resources/com/puresol/coding/lang/cpp/files");
	File sourceFile = new File(directory, "RecursiveIncludeMacros1.txt");
	SourceCode sourceCode = new FileSource(sourceFile).load();
	SourceCode preProcessedSourceCode = new CPreprocessor()
		.process(sourceCode);

	SourceCode sourceWithoutMacros = new FileSource(new File(directory,
		"FileWithoutMacros.txt")).load();
	SourceCode sourceWithoutMacros2 = new FileSource(new File(directory,
		"FileWithoutMacros2.txt")).load();

	SourceCode expected = new SourceCode();
	File file = new File(directory, "RecursiveIncludeMacros3.txt");
	expected.addSourceCodeLine(new SourceCodeLine(new FileSource(file), 1,
		"<end of file>"));
	expected.addSourceCode(sourceWithoutMacros2);
	expected.addSourceCode(sourceWithoutMacros);

	assertEquals(expected, preProcessedSourceCode);
    }
}
