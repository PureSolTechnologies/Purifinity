package com.puresoltechnologies.purifinity.server.accountmanager.rest.api;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.misc.types.EmailAddress;
import com.puresoltechnologies.commons.misc.types.Password;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.Role;
import com.puresoltechnologies.purifinity.server.accountmanager.domain.User;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordActivationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordChangeException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordCreationException;
import com.puresoltechnologies.purifinity.server.passwordstore.domain.PasswordResetException;

@Path("/")
public interface AccountManagerRestInterface {

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<User> getUsers();

    @GET
    @Path("roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Role> getRoles();

    @PUT
    @Path("users/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setUser(@PathParam("email") EmailAddress email, User user);

    @GET
    @Path("users/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("email") EmailAddress email);

    @PUT
    @Path("users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createAccount(EmailAddress email, Password password)
	    throws PasswordCreationException;

    @POST
    @Path("users/{email}/activate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EmailAddress activateAccount(@PathParam("email") EmailAddress email,
	    String activationKey) throws PasswordActivationException;

    @POST
    @Path("users/{email}/passwd")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean changePassword(@PathParam("email") EmailAddress email,
	    Password oldPassword, Password newPassword)
	    throws PasswordChangeException;

    @POST
    @Path("users/{email}/reset")
    @Produces(MediaType.APPLICATION_JSON)
    public Password resetPassword(@PathParam("email") EmailAddress email)
	    throws PasswordResetException;

}
