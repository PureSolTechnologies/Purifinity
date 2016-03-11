import {PurifinityServerConnector} from './PurifinityServerConnector';

import {Injectable} from 'angular2/core';

@Injectable()
export class ProjectManager {
    
    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getProjects(success: (data: Project[], status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects",
            success, error);
    }

    getProject(projectId: string,
        success: (data: Project, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId,
            success, error);
    }

    getLastRun(projectId: string,
        success: (data: any, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId + "/lastrun",
            success, error);
    }

    getRun(projectId: string,
        runId: string,
        success: (data: any, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId + "/runs/" + runId,
            success, error);
    }

    createProject(identifier: string,
        projectSettings: any,
        success: (data: any, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.put("/purifinityserver/rest/projectmanager/projects/" + identifier, projectSettings,
            success, error);
    }

    triggerNewRun(identifier: string,
        success: (data: any, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.put("/purifinityserver/rest/analysis/projects/" + identifier, "",
            success, error);
    }

    updateProjectSettings(identifier: string,
        settings: ProjectSettings,
        success: (data: any, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.post("/purifinityserver/rest/projectmanager/projects/" + identifier, settings,
            success, error);
    }

    deleteProject(identifier: string,
        success: (data: any, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.del("/purifinityserver/rest/projectmanager/projects/" + identifier,
            success, error);
    }

    getRepositoryTypes(success: (data: any, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/repositories/types",
            success, error);
    }

    readAllRunInformation(projectId: string,
        success: (data: any, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId + "/runs",
            success, error);
    }

    getAnalysisFileTree(projectId: string,
        runId: string,
        success: (data: any, status: string) => void,
        error: (data: any, status: string, error: string) => void) {
        return this.purifinityServerConnector.get("/purifinityserver/rest/projectmanager/projects/" + projectId + "/runs/" + runId + "/filetree",
            function(data: any, status) {
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
                success(data, status);
            },
            error
            );
    }

}
