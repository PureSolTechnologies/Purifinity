package com.puresoltechnologies.purifinity.server.passwordstore.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.misc.types.EmailAddress;
import com.puresoltechnologies.commons.misc.types.Password;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

/**
 * This is the central REST interface for {@link PasswordStore}.
 * 
 * @author Rick-Rainer Ludwig
 */
@Path("rest")
public interface PasswordStoreRestInterface {

    @PUT
    @Path("createAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createPassword(PasswordCreationEntity entity)
	    throws PasswordCreationException;

    @PUT
    @Path("activateAcount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EmailAddress activatePassword(PasswordActivationEntity entity)
	    throws PasswordActivationException;

    @POST
    @Path("authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean authenticate(PasswordAuthenticationEntity entity);

    @POST
    @Path("changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changePassword(PasswordChangeEntity entity)
	    throws PasswordChangeException;

    @POST
    @Path("resetPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Password resetPassword(EmailAddress emailAddress)
	    throws PasswordResetException;
}
