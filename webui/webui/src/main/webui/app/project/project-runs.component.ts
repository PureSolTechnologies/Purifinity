import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';

import {ProjectMenuComponent} from './project-menu.component';

@Component({
    selector: 'project-runs',
    directives: [
        ProjectMenuComponent
    ],
    templateUrl: '../../html/project/runs.html'
})
export class ProjectRunsComponent {

    private routeParams: RouteParams;
    private projectId: string;

    constructor(routeParams: RouteParams) {
        this.routeParams = routeParams;
    }
}
