package com.puresol.coding.lang.cpp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.uhura.lexer.SourceCode;
import com.puresol.uhura.preprocessor.PreprocessorException;

public class CPreProcessorTest {

    @Test
    public void testInstantiation() {
	assertNotNull(new CPreprocessor());
    }

    @Test
    public void testImmutabilityWithoutMacros() throws IOException,
	    PreprocessorException {
	SourceCode sourceCode = SourceCode.read(new File(
		"files/FileWithoutMacros.txt"));
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
	fail("Not implemented, yet!");
    }

    @Test
    public void testMultipleRecursiveIncludes() throws IOException,
	    PreprocessorException {
	fail("Not implemented, yet!");
    }
}
