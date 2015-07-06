package com.puresoltechnologies.purifinity.server.rest.api;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.common.rest.security.RolesAllowed;
import com.puresoltechnologies.purifinity.server.domain.evaluation.EvaluatorServiceInformation;

@Path("evaluation")
public interface EvaluationRestInterface {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("evaluators")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public Collection<EvaluatorServiceInformation> getEvaluators() throws IOException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("evaluators/{id}")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public EvaluatorServiceInformation getEvaluator(@PathParam("id") String evaluatorId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("evaluators/{id}/configuration")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public List<ConfigurationParameter<?>> getConfiguration(@PathParam("id") String evaluatorId);

    @GET
    @Path("evaluators/{id}/enabled")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public boolean isEnabled(@PathParam("id") String evaluatorId);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("evaluators/{id}/enable")
    public void enable(@PathParam("id") String evaluatorId);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("evaluators/{id}/disable")
    public void disable(@PathParam("id") String evaluatorId);

}
