package com.puresoltechnologies.purifinity.coding.lang.c11.preprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresoltechnologies.parser.impl.preprocessor.PreprocessorException;
import com.puresoltechnologies.parser.impl.source.CodeLocation;
import com.puresoltechnologies.parser.impl.source.SourceCode;
import com.puresoltechnologies.parser.impl.source.SourceCodeLine;
import com.puresoltechnologies.parser.impl.source.SourceFileLocation;

public class C11PreprocessorTest {

	@Test
	public void testInstance() {
		assertNotNull(new C11Preprocessor());
	}

	@Test
	public void testImmutabilityWithoutMacros() throws IOException,
			PreprocessorException {
		File directory = new File(
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		SourceCode sourceCode = new SourceFileLocation(directory,
				"FileWithoutMacros.txt").loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);
		assertEquals(sourceCode, preProcessedSourceCode);
		assertNotSame(sourceCode, preProcessedSourceCode);
	}

	@Test
	public void testSingleInclude() throws IOException, PreprocessorException {
		File directory = new File(
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		SourceCode sourceCode = new SourceFileLocation(directory,
				"SingleIncludeMacro.txt").loadSourceCode();
		SourceCode includedSourceCode = new SourceFileLocation(directory,
				"FileWithoutMacros.txt").loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);
		assertEquals(includedSourceCode, preProcessedSourceCode);
	}

	@Test
	public void testMultipleIncludes() throws IOException,
			PreprocessorException {
		File directory = new File(
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		CodeLocation source = new SourceFileLocation(directory,
				"MultipleIncludeMacros.txt");
		SourceCode sourceCode = source.loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);

		SourceCode sourceWithoutMacros = new SourceFileLocation(directory,
				"FileWithoutMacros.txt").loadSourceCode();
		SourceCode sourceWithoutMacros2 = new SourceFileLocation(directory,
				"FileWithoutMacros2.txt").loadSourceCode();

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
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		SourceCode sourceCode = new SourceFileLocation(directory,
				"RecursiveIncludeMacros1.txt").loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);

		SourceCode sourceWithoutMacros = new SourceFileLocation(directory,
				"FileWithoutMacros.txt").loadSourceCode();
		SourceCode sourceWithoutMacros2 = new SourceFileLocation(directory,
				"FileWithoutMacros2.txt").loadSourceCode();

		SourceCode expected = new SourceCode();
		expected.addSourceCodeLine(new SourceCodeLine(new SourceFileLocation(
				directory, "RecursiveIncludeMacros3.txt"), 1, "<end of file>"));
		expected.addSourceCode(sourceWithoutMacros2);
		expected.addSourceCode(sourceWithoutMacros);

		assertEquals(expected, preProcessedSourceCode);
	}

	@Test
	public void testSimpleDefine() throws IOException, PreprocessorException {
		File directory = new File(
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		SourceFileLocation sourceLocation = new SourceFileLocation(directory,
				"SimpleDefineTest.txt");
		SourceCode sourceCode = sourceLocation.loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);

		SourceCode expected = new SourceCode();
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 2, "1\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 4,
				"\"Hello, world!\"\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 6,
				"HELLO_WORLD\n"));
		assertEquals(expected, preProcessedSourceCode);
	}

	@Test
	public void testObjectLikeDefine() throws IOException,
			PreprocessorException {
		File directory = new File(
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		SourceFileLocation sourceLocation = new SourceFileLocation(directory,
				"ObjectLikeDefineTest.txt");
		SourceCode sourceCode = sourceLocation.loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);

		SourceCode expected = new SourceCode();
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 2,
				"Call the printf(\"This is a simple object macro!\"); macro now...\n"));
		assertEquals(expected, preProcessedSourceCode);
	}

	@Test
	public void testSimpleFunctionLikeDefine() throws IOException,
			PreprocessorException {
		File directory = new File(
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		SourceFileLocation sourceLocation = new SourceFileLocation(directory,
				"SimpleFunctionLikeDefineTest.txt");
		SourceCode sourceCode = sourceLocation.loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);

		SourceCode expected = new SourceCode();
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 2,
				"fprintf(stderr, \"%s\\\\n\", \"Error message!\");\n"));
		assertEquals(expected, preProcessedSourceCode);
	}

	@Test
	public void testFunctionLikeDefineTestWithMultipleLinesAndComment()
			throws IOException, PreprocessorException {
		File directory = new File(
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		SourceFileLocation sourceLocation = new SourceFileLocation(directory,
				"FunctionLikeDefineTestWithMultipleLinesAndComment.txt");
		SourceCode sourceCode = sourceLocation.loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);

		SourceCode expected = new SourceCode();
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 3, "\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 4,
				"    fprintf(stderr, \"%s\\\\n\", \n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 5,
				"// This is a comment\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 6,
				"\"Error message!\"\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 7,
				"/* This is another comment\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 8,
				"   with multiple lines! */\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 9, ");\n"));
		assertEquals(expected, preProcessedSourceCode);
	}

	@Test
	public void testSimpleIf() throws IOException, PreprocessorException {
		File directory = new File(
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		SourceFileLocation sourceLocation = new SourceFileLocation(directory,
				"SimpleIfTest.txt");
		SourceCode sourceCode = sourceLocation.loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);

		SourceCode expected = new SourceCode();
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 4,
				"Else shown...\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 7,
				"Shown...\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 13,
				"Shown...\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 20,
				"Else shown...\n"));
		assertEquals(expected, preProcessedSourceCode);
	}

	@Test
	public void testComplexIf() throws IOException, PreprocessorException {
		File directory = new File(
				"src/test/resources/com/puresoltechnologies/purifinity/coding/lang/c11/preprocessor/files");
		SourceFileLocation sourceLocation = new SourceFileLocation(directory,
				"ComplexIfTest.txt");
		SourceCode sourceCode = sourceLocation.loadSourceCode();
		SourceCode preProcessedSourceCode = new C11Preprocessor()
				.process(sourceCode);

		SourceCode expected = new SourceCode();
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 5,
				"Calculation valid.\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 8,
				"Calculation valid.\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 12,
				"Calculation invalid.\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 15,
				"Calculation valid.\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 19,
				"Calculation valid.\n"));
		expected.addSourceCodeLine(new SourceCodeLine(sourceLocation, 25,
				"Calculation valid.\n"));
		assertEquals(expected, preProcessedSourceCode);
	}

}
