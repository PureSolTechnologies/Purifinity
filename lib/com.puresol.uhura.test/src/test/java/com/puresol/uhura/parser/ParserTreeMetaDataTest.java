package com.puresol.uhura.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ParserTreeMetaDataTest {

    @Test
    public void testInstance() {
	assertNotNull(new ParserTreeMetaData("test.java", 1, 2));
    }

    @Test
    public void testInitValues() {
	ParserTreeMetaData metaData = new ParserTreeMetaData("test.java", 1, 2);
	assertEquals("test.java", metaData.getSourceName());
	assertEquals(1, metaData.getLine());
	assertEquals(2, metaData.getLineNum());
    }

    @Test
    public void testEquals() {
	assertEquals(new ParserTreeMetaData("test.java", 1, 1),
		new ParserTreeMetaData("test.java", 1, 1));
    }

    @Test
    public void testToString() {
	ParserTreeMetaData metaData = new ParserTreeMetaData("test.java", 1, 1);
	assertEquals("test.java: 1", metaData.toString());

	metaData = new ParserTreeMetaData("test.java", 1, 2);
	assertEquals("test.java: 1 - 2", metaData.toString());
    }
}
