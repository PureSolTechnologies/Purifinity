import {Injectable} from 'angular2/core';
import {Response} from 'angular2/http';

import {PurifinityServerConnector} from './PurifinityServerConnector';
import {Project} from '../domain/Project';
import {ProjectSettings} from '../domain/ProjectSettings';

@Injectable()
export class ProjectManager {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getProjects(success: (data: Project[]) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects",
            response => success(response.json()), error);
    }

    getProject(projectId: string,
        success: (data: Project) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId,
            response => success(response.json()), error);
    }

    getLastRun(projectId: string,
        success: (data: any, status: string) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId + "/lastrun",
            response => success(response.json(), response.statusText), error);
    }

    getRun(projectId: string,
        runId: string,
        success: (data: any, status: string) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId + "/runs/" + runId,
            response => success(response.json(), response.statusText), error);
    }

    createProject(identifier: string,
        projectSettings: any,
        success: (data: any, status: string) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.put("/purifinityserver/rest/projectmanager/projects/" + identifier, projectSettings,
            response => success(response.json(), response.statusText), error);
    }

    triggerNewRun(identifier: string,
        success: () => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.put("/purifinityserver/rest/analysis/projects/" + identifier, "",
            response => success(), error);
    }

    updateProjectSettings(identifier: string,
        settings: any,
        success: (data: any, status: string) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.post("/purifinityserver/rest/projectmanager/projects/" + identifier, settings,
            response => success(response, response.statusText), error);
    }

    deleteProject(identifier: string,
        success: (data: any, status: string) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.del("/purifinityserver/rest/projectmanager/projects/" + identifier,
            response => success(response.json(), response.statusText), error);
    }

    getRepositoryTypes(success: (data: any, status: string) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/repositorymanager/repositories",
            response => success(response.json(), response.statusText), error);
    }

    readAllRunInformation(projectId: string,
        success: (data: any, status: string) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId + "/runs",
            response => success(response.json(), response.statusText), error);
    }

    getAnalysisFileTree(projectId: string,
        runId: string,
        success: (data: any, status: string) => void,
        error: (response: Response) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId + "/runs/" + runId + "/filetree",
            function(response: Response) {
                var data = response.json();
                data.files = {};
                data.directories = {};
                var searchFileTree = function(tree) {
                    var hashId = tree.hashId.algorithmName + ":" + tree.hashId.hash;
                    if (tree.file) {
                        data.files[hashId] = tree;
                    } else {
                        data.directories[hashId] = tree;
                    }
                    if (tree.children.length > 0) {
                        tree.children.forEach(searchFileTree);
                    }
                };
                searchFileTree(data);
                data.getFile = function(hashid) {
                    return this.files[hashid];
                };
                data.getDirectory = function(hashid) {
                    return this.directories[hashid];
                };
                success(data, response.statusText);
            },
            error
        );
    }

}
