import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {ProjectManager} from '../commons/purifinity/ProjectManager';
import {Project} from '../commons/domain/Project';
import {ProjectMenuComponent} from './project-menu.component';
import {DefaultDatePipe} from '../commons/pipes/default-date.pipe';
import {DurationPipe} from '../commons/pipes/duration.pipe';

@Component({
    selector: 'project-runs',
    directives: [
        ROUTER_DIRECTIVES,
        ProjectMenuComponent
    ],
    pipes: [
        DefaultDatePipe,
        DurationPipe
    ],
    templateUrl: '../../html/project/runs.html'
})
export class ProjectRunsComponent {

    private projectId: string;

    private project: Project = undefined;
    private runs: any = [];

    constructor(private routeParams: RouteParams, private projectManager: ProjectManager) {
        this.projectId = routeParams.get('projectId');
        let projectRunsComponent = this;
        projectManager.getProject(this.projectId,
            function(project) {
                projectRunsComponent.project = project;
            },
            function(response: Response) {
            }
        );
        projectManager.readAllRunInformation(this.projectId,
            function(data, status) {
                projectRunsComponent.runs = data;
            },
            function(response: Response) {
            }
        );
    }

}
