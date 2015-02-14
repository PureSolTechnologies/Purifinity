package com.puresoltechnologies.purifinity.server.accountmanager.rest.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.puresoltechnologies.commons.types.IllegalEmailAddressException;

@Provider
public class IllegalEmailAddressExceptionRestFilter implements
	ExceptionMapper<IllegalEmailAddressException> {

    @Override
    public Response toResponse(IllegalEmailAddressException exception) {
	Response.Status httpStatus = Response.Status.NOT_ACCEPTABLE;
	return Response.status(httpStatus).entity(exception.getMessage())
		.build();
    }
}
