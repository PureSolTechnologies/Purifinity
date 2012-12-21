package com.puresol.coding.lang.cpp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
	InputStream sample = CPreprocessor.class
		.getResourceAsStream("files/FileWithoutMacros.txt");
	try {
	    SourceCode sourceCode = SourceCode.read(sample, new File(
		    "files/FileWithoutMacros.txt"));
	    SourceCode preProcessedSourceCode = new CPreprocessor()
		    .process(
			    new File(
				    "src/test/resources/com/puresol/coding/lang/cpp/files"),
			    sourceCode);
	    assertEquals(sourceCode, preProcessedSourceCode);
	    assertNotSame(sourceCode, preProcessedSourceCode);
	} finally {
	    sample.close();
	}
    }

    @Test
    public void testSingleInclude() throws IOException, PreprocessorException {
	InputStream sample = CPreprocessor.class
		.getResourceAsStream("files/SingleIncludeMacro.txt");
	try {
	    InputStream includedFile = CPreprocessor.class
		    .getResourceAsStream("files/FileWithoutMacros.txt");
	    try {
		File directory = new File(
			"src/test/resources/com/puresol/coding/lang/cpp/files");
		SourceCode sourceCode = SourceCode.read(sample, new File(
			directory, "SingleIncludeMacro.txt"));
		SourceCode includedSourceCode = SourceCode.read(includedFile,
			new File(directory, "FileWithoutMacros.txt"));
		SourceCode preProcessedSourceCode = new CPreprocessor()
			.process(directory, sourceCode);
		assertEquals(includedSourceCode, preProcessedSourceCode);
	    } finally {
		includedFile.close();
	    }
	} finally {
	    sample.close();
	}
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
