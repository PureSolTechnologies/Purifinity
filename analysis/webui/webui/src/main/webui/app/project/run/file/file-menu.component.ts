import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

@Component({
    selector: 'file-menu',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: '../../html/project/run/file/file-menu.html'
})
export class FileMenuComponent {

    private projectId: string;
    private runId: string;
    private hashId: string;

    constructor(private routeParams: RouteParams) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');
        this.hashId = routeParams.get('hashId');
    }

}
