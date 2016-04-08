import {Injectable} from 'angular2/core';
import {Response} from 'angular2/http';

import {PurifinityServerConnector} from './PurifinityServerConnector';

@Injectable()
export class FileStore {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    isAvailable(hashId: string, success: (available: boolean) => void, error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/filestore/files/" + hashId + "/available",
            (response) => success(response.json()), error);
    }

    readSourceCode(hashId: string, success, error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/filestore/files/" + hashId + "/sourcecode",
            success, error);
    }

    loadAnalyses(hashId: string, success: (analyses: any[]) => void, error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/filestore/files/" + hashId + "/analyses",
             (response) => success(response.json()), error);
    }

    wasAnalyzed(hashId:  string, success, error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/filestore/files/" + hashId + "/analyzed",
            success, error);
    }

}