import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {ProjectManager} from '../../commons/purifinity/ProjectManager';
import {ProjectRunMenuComponent} from './project-run-menu.component';

@Component({
    selector: 'project-run-defects',
    directives: [
        ProjectRunMenuComponent
    ],
    templateUrl: '../../html/project/run/defects.html'
})
export class ProjectRunDefectsComponent {

    private projectId: string;
    private runId: string;

    constructor(private routeParams: RouteParams, private projectManager: ProjectManager) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');
    }

}
