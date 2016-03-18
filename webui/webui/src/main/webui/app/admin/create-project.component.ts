import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, Router} from 'angular2/router';
import {Response} from 'angular2/http';

import {ProjectManager} from '../commons/purifinity/ProjectManager';
import {Project} from '../commons/domain/Project';
import {RepositoryType} from '../commons/domain/RepositoryType';

@Component({
    selector: 'create-project',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: '../../html/admin/create-project.html'
})
export class CreateProjectComponent {

    private router: Router;
    private projectManager: ProjectManager;

    public id: string = "";
    public name: string = "";
    public description: string = "";
    public directoryIncludes: string = "";
    public directoryExcludes: string = "bin\nbuild\ntarget\n.*";
    public fileIncludes: string = "";
    public fileExcludes: string = ".*\n*.bak\n*~\n#*#";
    public ignoreHidden: boolean = true;
    public repositories: RepositoryType[];
    public repositoryId: string = "";
    public repositoryProperties: any = [];

    constructor(router: Router, projectManager: ProjectManager) {
        this.router = router;
        this.projectManager = projectManager;

        var createProjectComponent = this;
        projectManager.getRepositoryTypes(
            function(repositories: RepositoryType[]) { createProjectComponent.repositories = repositories; }, //
            function(response: Response) { });
    }

    selectRepository(newRepositoryId: string) {
        this.repositoryId = newRepositoryId;
        var key;
        for (key in this.repositories) {
            var repository = this.repositories[key];
            if (repository.id === this.repositoryId) {
                this.repositoryProperties = [];
                var name;
                for (name in repository.parameters) {
                    let parameter: any = repository.parameters[name];
                    parameter.uiName = name;
                    this.repositoryProperties.push(parameter);
                }
            }
        }
    }

    disableOK(): boolean {
        if (!this.id) {
            return true;
        }
        if (!this.name) {
            return true;
        }
        if (!this.repositoryId) {
            return true;
        }
        if (!this.repositoryProperties) {
            return true;
        }
        return false;
    }

    createProject() {
        let projectSettings = {
            "name": this.name,
            "description": this.description,
            "fileSearchConfiguration": {
                "locationIncludes": this.directoryIncludes.split("\n"),
                "locationExcludes": this.directoryExcludes.split("\n"),
                "fileIncludes": this.fileIncludes.split("\n"),
                "fileExcludes": this.fileExcludes.split("\n"),
                "ignoreHidden": this.ignoreHidden
            },
            "repository": {
                "repository.id": this.repositoryId
            }
        };
        for (var key in this.repositoryProperties) {
            projectSettings.repository[key] = this.repositoryProperties[key];
        }
        let createProjectComponent = this;
        this.projectManager.createProject(this.id, projectSettings,
            function(response: Response) {
                createProjectComponent.router.navigate(['/ProjectsAdmin']);
            }, //
            function(response: Response) { });
    }


}
