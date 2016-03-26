import {Injectable} from 'angular2/core';
import {Response} from 'angular2/http';

import {AnalyzerServiceInformation} from '../plugins/AnalyzerServiceInformation';
import {EvaluatorServiceInformation} from '../plugins/EvaluatorServiceInformation';
import {RepositoryServiceInformation} from '../plugins/RepositoryServiceInformation';
import {PurifinityServerConnector} from './PurifinityServerConnector';

@Injectable()
export class PluginManager {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getAnalyzers(success: (analyzers: AnalyzerServiceInformation[]) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/analysis/analyzers",
            response => success(response.json()), error);
    }

    getEvaluators(success: (evaluators: EvaluatorServiceInformation[]) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/evaluation/evaluators",
            response => success(response.json()), error);
    }

    getRepositoryTypes(success: (repositories: RepositoryServiceInformation[]) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/repositories/types",
            response => success(response.json()), error);
    }

    isAnalyzerEnabled(analyzerId, success: (enabled: boolean) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get_text("/purifinityserver/rest/analysis/analyzers/" + analyzerId + "/enabled",
            response => success(response.json()), error);
    }

    enableAnalyzer(analyzerId: string, success: () => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.put("/purifinityserver/rest/analysis/analyzers/" + analyzerId + "/enable", "",
            response => success(), error);
    }

    disableAnalyzer(analyzerId, success: () => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.put("/purifinityserver/rest/analysis/analyzers/" + analyzerId + "/disable", "",
            response => success(), error);
    }

    isEvaluatorEnabled(analyzerId, success: (enabled: boolean) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get_text("/purifinityserver/rest/evaluation/evaluators/" + analyzerId + "/enabled",
            response => success(response.json()), error);
    }

    enableEvaluator(evaluatorId, success: () => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.put("/purifinityserver/rest/evaluation/evaluators/" + evaluatorId + "/enable", "",
            response => success(), error);
    }

    disableEvaluator(evaluatorId, success: () => void,
        error: (response: Response) => void) {
        this.purifinityServerConnector.put("/purifinityserver/rest/evaluation/evaluators/" + evaluatorId + "/disable", "",
            response => success(), error);
    }
}
