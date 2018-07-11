package com.puresoltechnologies.toolshed.commons;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toString(Object o) throws JsonProcessingException {
	return mapper.writeValueAsString(o);
    }

    public static <T> T fromString(String serialized, Class<T> type)
	    throws JsonParseException, JsonMappingException, IOException {
	return mapper.readValue(serialized, type);
    }

    public static <T> T fromInputStream(InputStream inputStream, Class<T> clazz)
	    throws JsonParseException, JsonMappingException, IOException {
	return mapper.readValue(inputStream, clazz);
    }

    public static <T> MappingIterator<T> fromCollectionInputStream(InputStream inputStream, Class<T> clazz)
	    throws JsonParseException, JsonMappingException, IOException {
	return mapper.readerFor(clazz).readValues(inputStream);
    }

}
