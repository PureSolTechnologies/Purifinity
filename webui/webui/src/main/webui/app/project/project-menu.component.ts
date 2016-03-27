import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

@Component({
    selector: 'project-menu',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: '../../html/project/project-menu.html'
})
export class ProjectMenuComponent {

    private projectId: string;

    constructor(private routeParams: RouteParams) {
        this.projectId = routeParams.get('projectId');
    }

}
