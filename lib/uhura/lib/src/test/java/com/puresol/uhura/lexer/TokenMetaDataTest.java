package com.puresol.uhura.lexer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import com.puresol.uhura.source.UnspecifiedSourceCodeLocation;

public class TokenMetaDataTest {

    @Test
    public void testInstance() {
	assertNotNull(new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1,
		2, 3));
    }

    @Test
    public void testInitValues() {
	TokenMetaData metaData = new TokenMetaData(
		new UnspecifiedSourceCodeLocation(), 1, 2, 3);
	assertEquals(
		new UnspecifiedSourceCodeLocation()
			.getHumanReadableLocationString(),
		metaData.getSource().getHumanReadableLocationString());
	assertEquals(1, metaData.getLine());
	assertEquals(2, metaData.getPos());
	assertEquals(3, metaData.getLineNum());
    }

    @Test
    public void testClone() {
	TokenMetaData metaData = new TokenMetaData(
		new UnspecifiedSourceCodeLocation(), 1, 2, 3);
	TokenMetaData cloned = metaData.clone();
	assertNotSame(metaData, cloned);
	assertEquals(metaData, cloned);
    }
}
