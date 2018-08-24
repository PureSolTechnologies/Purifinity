package com.puresoltechnologies.purifinity.server.passwordstore.rest.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

@Provider
public class PasswordResetExceptionRestFilter implements
	ExceptionMapper<PasswordResetException> {

    @Override
    public Response toResponse(PasswordResetException exception) {
	Response.Status httpStatus = Response.Status.NOT_ACCEPTABLE;
	return Response.status(httpStatus).entity(exception.getMessage())
		.build();
    }
}
