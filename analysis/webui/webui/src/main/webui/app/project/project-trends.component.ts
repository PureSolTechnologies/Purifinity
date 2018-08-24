import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';

import {ProjectMenuComponent} from './project-menu.component';

@Component({
    selector: 'project-trends',
    directives: [
        ProjectMenuComponent
    ],
    templateUrl: '../../html/project/trends.html'
})
export class ProjectTrendsComponent {

    private routeParams: RouteParams;
    private projectId: string;

    constructor(routeParams: RouteParams) {
        this.routeParams = routeParams;
    }
}
