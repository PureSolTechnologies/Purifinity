import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

@Component({
    selector: 'project-run-menu',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: '../../html/project/run/project-run-menu.html'
})
export class ProjectRunMenuComponent {

    private projectId: string;
    private runId: string;

    constructor(private routeParams: RouteParams) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');
    }

}
