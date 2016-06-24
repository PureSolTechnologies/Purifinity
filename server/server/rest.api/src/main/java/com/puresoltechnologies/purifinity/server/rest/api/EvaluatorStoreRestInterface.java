package com.puresoltechnologies.purifinity.server.rest.api;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Classification;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.RunIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.RunMetrics;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.common.rest.security.RolesAllowed;

@Path("evaluatorstore")
public interface EvaluatorStoreRestInterface {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/metrics/evaluators/{evaluator_id}")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public RunMetrics getRunMetrics(@PathParam("project_id") String projectId, @PathParam("run_id") long runId,
	    @PathParam("evaluator_id") String evaluatorId) throws EvaluationStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/issues/evaluators/{evaluator_id}")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public RunIssues getRunIssues(@PathParam("project_id") String projectId, @PathParam("run_id") long runId,
	    @PathParam("evaluator_id") String evaluatorId) throws EvaluationStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/issues/severities")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public Map<Severity, Integer> getRunIssuesByServerity(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws EvaluationStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/issues/classification")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public Map<Classification, Integer> getRunIssuesByClassification(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws EvaluationStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/issues/architecture/severities")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public Map<Severity, Integer> getRunIssueArchitectureServerities(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws EvaluationStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/issues/design/severities")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public Map<Severity, Integer> getRunIssueDesignServerities(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws EvaluationStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/issues/defect/severities")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public Map<Severity, Integer> getRunIssueDefectServerities(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws EvaluationStoreException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("projects/{project_id}/runs/{run_id}/issues/style/severities")
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public Map<Severity, Integer> getRunIssueStyleServerities(@PathParam("project_id") String projectId,
	    @PathParam("run_id") long runId) throws EvaluationStoreException;

}
