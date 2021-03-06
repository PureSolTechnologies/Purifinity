package com.puresoltechnologies.commons.domain;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

public class JSONSerializer {

    public static String toJSONString(Object o) throws JsonGenerationException, JsonMappingException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	mapper.findAndRegisterModules();
	mapper.registerModule(new JSR310Module());
	ObjectWriter writer = mapper.writerFor(o.getClass());
	return writer.writeValueAsString(o);
    }

    public static <T> T fromJSONString(String string, Class<T> type)
	    throws JsonGenerationException, JsonMappingException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	mapper.findAndRegisterModules();
	mapper.registerModule(new JSR310Module());
	ObjectReader reader = mapper.reader(type);
	return reader.readValue(string);
    }

    public static <T> T fromJSONString(Reader reader, Class<T> type)
	    throws JsonGenerationException, JsonMappingException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	mapper.findAndRegisterModules();
	mapper.registerModule(new JSR310Module());
	ObjectReader objectReader = mapper.reader(type);
	return objectReader.readValue(reader);
    }

    public static <T> T fromJSONString(InputStream inputStream, Class<T> type)
	    throws JsonGenerationException, JsonMappingException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	mapper.findAndRegisterModules();
	mapper.registerModule(new JSR310Module());
	ObjectReader reader = mapper.reader(type);
	return reader.readValue(inputStream);
    }

}
