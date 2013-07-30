package com.puresol.commons.license.domain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class JsonSerializer {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static <T> void serializeToStream(T value, OutputStream outputStream)
			throws IOException {
		ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
		writer.writeValue(outputStream, value);
	}

	public static <T> T deserialize(InputStream input, Class<? extends T> clazz)
			throws IOException {
		return mapper.readValue(input, clazz);
	}
}
