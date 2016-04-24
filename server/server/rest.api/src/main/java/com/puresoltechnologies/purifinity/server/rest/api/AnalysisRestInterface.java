package com.puresoltechnologies.purifinity.server.rest.api;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.common.rest.security.RolesAllowed;
import com.puresoltechnologies.purifinity.server.domain.analysis.AnalyzerServiceInformation;

@Path("analysis")
public interface AnalysisRestInterface {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("analyzers")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public Collection<AnalyzerServiceInformation> getAnalyzers() throws IOException;

    @PUT
    @Path("projects/{project_id}")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID })
    void triggerNewRun(@PathParam("project_id") String projectId);

    @PUT
    @Path("projects/{project_id}/abort/{job_id}")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID })
    void abortRun(@PathParam("project_id") String projectId, @PathParam("job_id") long jobId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("analyzers/{id}")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public AnalyzerServiceInformation getAnalyzer(@PathParam("id") String analyzerId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("analyzers/{id}/configuration")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public ConfigurationParameter<?>[] getConfiguration(@PathParam("id") String analyzerId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("analyzers/{id}/grammar")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public Grammar getGrammar(@PathParam("id") String analyzerId);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("analyzers/{id}/enabled")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public boolean isEnabled(@PathParam("id") String analyzerId);

    @PUT
    @Path("analyzers/{id}/enable")
    public void enable(@PathParam("id") String analyzerId);

    @PUT
    @Path("analyzers/{id}/disable")
    public void disable(@PathParam("id") String analyzerId);

}
