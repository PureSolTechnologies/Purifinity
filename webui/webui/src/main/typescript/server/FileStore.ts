class FileStore {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    isAvailable(hashId : string, success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/filestore/files/' + hashId + '/available',
            success, error);
    }

    readSourceCode(hashId : string, success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/filestore/files/' + hashId + '/sourcecode',
            success, error);
    }

    loadAnalyses(hashId : string, success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/filestore/files/' + hashId + '/analyses',
            success, error);
    }

    wasAnalyzed(hashId : string, success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/filestore/files/' + hashId + '/analyzed',
            success, error);
    }

}