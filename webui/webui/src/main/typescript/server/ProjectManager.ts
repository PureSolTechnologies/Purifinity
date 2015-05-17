class ProjectManager {

    constructor(private purifinityServerConnector: PurifinityServerConnector) {
    }

    getProjects(success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects',
            success, error);
    }

    getProject(projectId, success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId,
            success, error);
    }

    getLastRun(projectId, success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId + '/lastrun',
            success, error);
    }

    getRun(projectId, runId, success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId + '/runs/' + runId,
            success, error);
    }

    createProject(identifier, projectSettings, success, error) {
        return this.purifinityServerConnector.put('/purifinityserver/rest/projectmanager/projects/' + identifier, projectSettings,
            success, error);
    }

    triggerNewRun(identifier, success, error) {
        return this.purifinityServerConnector.put('/purifinityserver/rest/analysis/projects/' + identifier, "",
            success, error);
    }

    editProject(id, name, success, error) {
    }

    deleteProject(identifier, success, error) {
        return this.purifinityServerConnector.del('/purifinityserver/rest/projectmanager/projects/' + identifier,
            success, error);
    }

    getRepositoryTypes(success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/repositories/types',
            success, error);
    }

    readAllRunInformation(projectId, success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId + '/runs',
            success, error);
    }

    getAnalysisFileTree(projectId, runId, success, error) {
        return this.purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId + '/runs/' + runId + '/filetree',
            function(data : any, status) {
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
                }
                searchFileTree(data);
                data.getFile = function(hashid) {
                    return this.files[hashid];
                }
                data.getDirectory = function(hashid) {
                    return this.directories[hashid];
                }
                success(data, status);
            },
            error
            );
    }


}
