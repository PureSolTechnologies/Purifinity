package com.puresoltechnologies.purifinity.server.accountmanager.rest.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.AccountCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

@Provider
public class RestExceptionFilter implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
	if (AccountCreationException.class.equals(exception)) {
	    Response.Status httpStatus = Response.Status.NOT_ACCEPTABLE;
	    return Response.status(httpStatus).entity(exception.getMessage())
		    .build();
	} else if (AccountActivationException.class.equals(exception)) {
	    Response.Status httpStatus = Response.Status.NOT_ACCEPTABLE;
	    return Response.status(httpStatus).entity(exception.getMessage())
		    .build();
	} else if (PasswordChangeException.class.equals(exception)) {
	    Response.Status httpStatus = Response.Status.NOT_ACCEPTABLE;
	    return Response.status(httpStatus).entity(exception.getMessage())
		    .build();
	} else if (PasswordResetException.class.equals(exception)) {
	    Response.Status httpStatus = Response.Status.NOT_ACCEPTABLE;
	    return Response.status(httpStatus).entity(exception.getMessage())
		    .build();
	} else {
	    Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
	    return Response.status(httpStatus).entity(exception.getMessage())
		    .build();
	}
    }
}
