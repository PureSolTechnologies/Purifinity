import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';
import {Response} from 'angular2/http';

import {ProjectManager} from '../commons/purifinity/ProjectManager';
import {Project} from '../commons/domain/Project';

@Component({
    selector: 'projects-admin',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: '../../html/admin/projects-admin.html'
})
export class ProjectsAdminComponent {

    private projectManager: ProjectManager;
    private projects: Project[];

    constructor(projectManager: ProjectManager) {
        this.projectManager = projectManager;
        this.updateProjectList();
    }

    deleteProject(projectId: string) {
        var projectsAdminComponent = this;
        this.projectManager.deleteProject(projectId,
            function(response: Response) {
                projectsAdminComponent.updateProjectList();
            },
            function(response: Response) {
            }
        );
    }

    updateProjectList() {
        var projectsAdminComponent = this;
        this.projectManager.getProjects(//
            function(projects: Project[]) { projectsAdminComponent.projects = projects; }, //
            function(response: Response) { });

    }

    getProjects(): Project[] {
        return this.projects;
    }
}
