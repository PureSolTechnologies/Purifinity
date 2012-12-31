package com.puresol.coding.lang.c11.preprocessor;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresol.uhura.source.FixedCodeLocation;

public class C11PreprocessorTest {

    @Test
    public void testInstance() {
	assertNotNull(new C11Preprocessor());
    }

    @Test
    public void testParsingEmptyFile() throws Exception {
	C11Preprocessor preprocessor = new C11Preprocessor();
	FixedCodeLocation location = new FixedCodeLocation("");
	preprocessor.process(location.load());
    }

    @Test
    public void testParsingSimpleIncludeWithoutTrailingCR() throws Exception {
	C11Preprocessor preprocessor = new C11Preprocessor();
	FixedCodeLocation location = new FixedCodeLocation(
		"#include \"testfile.h\"");
	preprocessor.process(location.load());
    }

    @Test
    public void testParsingSimpleInclude1() throws Exception {
	C11Preprocessor preprocessor = new C11Preprocessor();
	FixedCodeLocation location = new FixedCodeLocation(
		"#include \"testfile.h\"\n");
	preprocessor.process(location.load());
    }

    @Test
    public void testParsingSimpleInclude2() throws Exception {
	C11Preprocessor preprocessor = new C11Preprocessor();
	FixedCodeLocation location = new FixedCodeLocation(
		"#include <testfile.h>\n");
	preprocessor.process(location.load());
    }

}
