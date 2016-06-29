import {Injectable} from 'angular2/core';
import {Response} from 'angular2/http';

import {PurifinityServerConnector} from './PurifinityServerConnector';

@Injectable()
export class AnalysisStore {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getPreAnalysisScriptOutput(projectId: string, runId: string, success: (data: string) => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.get("/purifinityserver/rest/analysis/projects/"
            + projectId
            + "/runs/"
            + runId
            + "/preanalysis/output", function(response: Response) {
                let data: string = response.text();
                success(data);
            }, error
        );

    }
}
