package com.puresol.uhura.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;

import com.puresol.uhura.source.FileSource;
import com.puresol.uhura.source.UnspecifiedSource;

public class ParserTreeMetaDataTest {

    @Test
    public void testInstance() {
	assertNotNull(new ParserTreeMetaData(new UnspecifiedSource(), 1, 2));
    }

    @Test
    public void testInitValues() {
	ParserTreeMetaData metaData = new ParserTreeMetaData(
		new UnspecifiedSource(), 1, 2);
	assertEquals(new UnspecifiedSource().getHumanReadableLocationString(),
		metaData.getSource().getHumanReadableLocationString());
	assertEquals(1, metaData.getLine());
	assertEquals(2, metaData.getLineNum());
    }

    @Test
    public void testEquals() {
	assertEquals(new ParserTreeMetaData(new FileSource(new File("Test")),
		1, 1), new ParserTreeMetaData(new FileSource(new File("Test")),
		1, 1));
    }

    @Test
    public void testToString() {
	ParserTreeMetaData metaData = new ParserTreeMetaData(
		new UnspecifiedSource(), 1, 1);
	assertEquals("unspecified source: 1", metaData.toString());

	metaData = new ParserTreeMetaData(new UnspecifiedSource(), 1, 2);
	assertEquals("unspecified source: 1 - 2", metaData.toString());
    }
}
