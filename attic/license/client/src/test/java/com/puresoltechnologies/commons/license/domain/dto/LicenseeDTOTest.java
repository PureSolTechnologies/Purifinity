package com.puresoltechnologies.commons.license.domain.dto;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import com.puresoltechnologies.commons.license.domain.JsonSerializer;
import com.puresoltechnologies.commons.license.domain.Licensee;
import com.puresoltechnologies.commons.license.domain.dto.LicenseeDTO;

public class LicenseeDTOTest {

	@Test
	public void testJsonSerialization() throws IOException {
		Licensee licensee = new Licensee("id", "licensee");

		LicenseeDTO licenseeDTO = new LicenseeDTO(licensee);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		JsonSerializer.serializeToStream(licenseeDTO, outputStream);
		byte[] serializedByteArray = outputStream.toByteArray();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(
				serializedByteArray);
		LicenseeDTO deserialized = JsonSerializer.deserialize(inputStream,
				LicenseeDTO.class);

		ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
		JsonSerializer.serializeToStream(deserialized, outputStream2);
		byte[] serializedByteArray2 = outputStream2.toByteArray();

		assertTrue(Arrays.equals(serializedByteArray, serializedByteArray2));
	}
}
