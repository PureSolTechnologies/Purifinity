import {Injectable} from 'angular2/core';
import {Response} from 'angular2/http';

import {PurifinityServerConnector} from './PurifinityServerConnector';
import {RunMetrics} from '../domain/metrics/RunMetrics';
import {RunIssues} from '../domain/issues/RunIssues';

@Injectable()
export class EvaluatorStore {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getRunMetrics(projectId: string, runId: string, evaluatorId: string, success: (data: RunMetrics) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/metrics/evaluators/" + evaluatorId, function(response: Response) {
                let data: RunMetrics = response.json();
                success(data);
            }, error
        );

    }

    getRunIssues(projectId: string, runId: string, evaluatorId: string, success: (data: RunIssues) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/evaluators/" + evaluatorId, function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

    getRunIssueSummaryBySeverity(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/severities", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

    getRunIssueSummaryByClassification(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/classification", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

    getRunIssueArchitectureSeverities(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/architecture/severities", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

    getRunIssueDesignSeverities(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/design/severities", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

    getRunIssueDefectSeverities(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/defect/severities", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

    getRunIssueStyleSeverities(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/style/severities", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

}
