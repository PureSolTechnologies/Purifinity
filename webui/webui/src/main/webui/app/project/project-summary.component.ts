import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';

import {ProjectMenuComponent} from './project-menu.component';

@Component({
    selector: 'project-summary',
    directives: [
        ProjectMenuComponent
    ],
    templateUrl: '../../html/project/summary.html'
})
export class ProjectSummaryComponent {

    private routeParams: RouteParams;
    private projectId: string;

    constructor(routeParams: RouteParams) {
        this.routeParams = routeParams;
    }
}
