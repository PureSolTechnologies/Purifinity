package com.puresoltechnologies.purifinity.server.passwordstore.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * This is the central REST interface for {@link PasswordStore}.
 * 
 * @author Rick-Rainer Ludwig
 */
@Path("rest")
public interface PasswordStoreRestInterface {

	@PUT
	@Path("createAccount")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response createAccount(String content);

	@PUT
	@Path("activateAcount")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response activateAccount(String content);

	@POST
	@Path("authenticate")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response authenticate(String content);

	@POST
	@Path("changePassword")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response changePassword(String content);

	@POST
	@Path("resetPassword")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response resetPassword(String content);
}
