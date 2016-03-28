import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {ProjectManager} from '../../commons/purifinity/ProjectManager';
import {ProjectRunMenuComponent} from './project-run-menu.component';

@Component({
    selector: 'project-run-architecture',
    directives: [
        ProjectRunMenuComponent
    ],
    templateUrl: '../../html/project/run/architecture.html'
})
export class ProjectRunArchitectureComponent {

    private projectId: string;
    private runId: string;

    constructor(private routeParams: RouteParams, private projectManager: ProjectManager) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');
    }

}
