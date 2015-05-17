class PluginManager {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getAnalyzers(success: (data: string, status: number) => void, 
        error: (data: string, status: number, error: string) => void) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/analysis/analyzers',
            success, error);
    }

    getEvaluators(success: (data: string, status: number) => void, 
        error: (data: string, status: number, error: string) => void) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/evaluation/evaluators',
            success, error);
    }

    getRepositoryTypes(success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/repositories/types',
            success, error);
    }

    isAnalyzerEnabled(analyzerId, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/analysis/analyzers/' + analyzerId + "/enabled",
            success, error);
    }

    enableAnalyzer(analyzerId: string, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        this.purifinityServerConnector.put('/purifinityserver/rest/analysis/analyzers/' + analyzerId + "/enable", "",
            success, error);
    }

    disableAnalyzer(analyzerId, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        this.purifinityServerConnector.put('/purifinityserver/rest/analysis/analyzers/' + analyzerId + "/disable", "",
            success, error);
    }

    isEvaluatorEnabled(analyzerId, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/evaluation/evaluators/' + analyzerId + "/enabled",
            success, error);
    }

    enableEvaluator(evaluatorId, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        this.purifinityServerConnector.put('/purifinityserver/rest/evaluation/evaluators/' + evaluatorId + "/enable", "",
            success, error);
    }

    disableEvaluator(evaluatorId, success: (data: string, status: number) => void,
        error: (data: string, status: number, error: string) => void) {
        this.purifinityServerConnector.put('/purifinityserver/rest/evaluation/evaluators/' + evaluatorId + "/disable", "",
            success, error);
    }
}
