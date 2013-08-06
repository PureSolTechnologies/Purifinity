package com.puresol.commons.license.domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

public class LicenseeTest {

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidIdNull() {
		new Licensee(null, "licensee");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNameEmpty() {
		new Licensee("", "licensee");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidEmailNull() {
		new Licensee("id", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidEmailEmpty() {
		new Licensee("id", "");
	}

	@Test
	public void testFromString() {
		assertEquals(new Licensee("id", "licensee"),
				Licensee.fromString("licensee (id)"));
	}

	@Test
	public void testJsonSerialization() throws IOException {
		Licensee licensee = new Licensee("id", "licensee");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JsonSerializer.serializeToStream(licensee, outputStream);
		byte[] serializedByteArray = outputStream.toByteArray();
		Licensee deserialized = JsonSerializer.deserialize(serializedByteArray,
				Licensee.class);

		ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
		JsonSerializer.serializeToStream(deserialized, outputStream2);
		byte[] serializedByteArray2 = outputStream2.toByteArray();

		assertArrayEquals(serializedByteArray, serializedByteArray2);
	}
}