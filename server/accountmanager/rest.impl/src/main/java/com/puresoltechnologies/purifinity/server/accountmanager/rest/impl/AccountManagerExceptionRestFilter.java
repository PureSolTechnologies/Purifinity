package com.puresoltechnologies.purifinity.server.accountmanager.rest.impl;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.puresoltechnologies.purifinity.server.accountmanager.core.api.AccountManagerException;

@Provider
public class AccountManagerExceptionRestFilter implements
	ExceptionMapper<AccountManagerException> {

    @Override
    public Response toResponse(AccountManagerException exception) {
	Response.Status httpStatus = Response.Status.NOT_ACCEPTABLE;
	return Response.status(httpStatus).entity(exception.getMessage())
		.build();
    }
}
