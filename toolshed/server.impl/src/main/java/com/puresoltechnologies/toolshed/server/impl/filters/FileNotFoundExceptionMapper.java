package com.puresoltechnologies.toolshed.server.impl.filters;

import java.io.FileNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class FileNotFoundExceptionMapper implements ExceptionMapper<FileNotFoundException> {

    private static final Logger logger = LoggerFactory.getLogger(FileNotFoundExceptionMapper.class);

    @Override
    public Response toResponse(FileNotFoundException exception) {
	logger.warn("Mapping file not found exception.", exception);
	return Response.status(Status.NOT_FOUND).entity(exception).build();
    }

}