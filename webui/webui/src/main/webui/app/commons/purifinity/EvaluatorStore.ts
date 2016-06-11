import {Injectable} from 'angular2/core';
import {Response} from 'angular2/http';

import {PurifinityServerConnector} from './PurifinityServerConnector';
import {RunMetrics} from '../domain/metrics/RunMetrics';

@Injectable()
export class EvaluatorStore {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getRunMetrics(projectId:string, runId:string, evaluatorId:string, success: (data: RunMetrics) => void,
        error: (response: Response) => void) {           
        this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/metrics/projects/"
                + projectId
                + "/runs/" + runId
                + "/evaluators/" + evaluatorId, function(response: Response) {
                    let data:RunMetrics = response.json();
                    success(data);
                }, error
            );

        }

        getRunIssues(projectId:string, runId:string, evaluatorId:string, success: (data: any) => void,
            error: (response: Response) => void) {           
            this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/issues/projects/"
                + projectId
                + "/runs/" + runId
                + "/evaluators/" + evaluatorId, function(response: Response) {
                    let data = response.json();
                    success(data);
                }, error
            );

        }
}
