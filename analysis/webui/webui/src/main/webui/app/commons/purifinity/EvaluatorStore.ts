import {Injectable} from 'angular2/core';
import {Response} from 'angular2/http';

import {PurifinityServerConnector} from './PurifinityServerConnector';
import {RunMetrics} from '../domain/metrics/RunMetrics';
import {RunIssues} from '../domain/issues/RunIssues';
import {SingleIssue} from '../domain/issues/SingleIssue';

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

    getFileIssues(projectId: string, runId: string, hashId: string, success: (data: SingleIssue[]) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId + "/runs/" + runId + "/files/" + hashId + "/issues", function(response: Response) {
                let data: SingleIssue[] = response.json();
                success(data);
            }, error
        );

    }

    getRunArchitectureIssues(projectId: string, runId: string, success: (data: SingleIssue[]) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/types/architecture", function(response: Response) {
                let data: SingleIssue[] = response.json();
                success(data);
            }, error
        );
    }

    getRunDesignIssues(projectId: string, runId: string, success: (data: SingleIssue[]) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/types/design", function(response: Response) {
                let data: SingleIssue[] = response.json();
                success(data);
            }, error
        );
    }

    getRunDefectIssues(projectId: string, runId: string, success: (data: SingleIssue[]) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/types/defects", function(response: Response) {
                let data: SingleIssue[] = response.json();
                success(data);
            }, error
        );
    }

    getRunStyleIssues(projectId: string, runId: string, success: (data: SingleIssue[]) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/types/style", function(response: Response) {
                let data: SingleIssue[] = response.json();
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

    getRunIssueArchitectureParameters(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/architecture/parameters", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

    getRunIssueDesignParameters(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/design/parameters", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

    getRunIssueDefectParameters(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/defect/parameters", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );

    }

    getRunIssueStyleParameters(projectId: string, runId: string, success: (data: any) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/projects/"
            + projectId
            + "/runs/" + runId
            + "/issues/style/parameters", function(response: Response) {
                let data: RunIssues = response.json();
                success(data);
            }, error
        );
    }

}
