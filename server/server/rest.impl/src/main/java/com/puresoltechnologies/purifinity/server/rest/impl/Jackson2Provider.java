package com.puresoltechnologies.purifinity.server.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Jackson2Provider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public Jackson2Provider() {
	mapper = new ObjectMapper();
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	mapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, true);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
	return mapper;
    }

}
