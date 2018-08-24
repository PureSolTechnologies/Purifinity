package com.puresoltechnologies.purifinity.server.accountmanager.rest.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.buschmais.xo.api.XOException;

@Provider
public class XOExceptionRestFilter implements ExceptionMapper<XOException> {

    @Override
    public Response toResponse(XOException exception) {
	Response.Status httpStatus = Response.Status.NOT_ACCEPTABLE;
	return Response.status(httpStatus).entity(exception.getMessage())
		.build();
    }
}
