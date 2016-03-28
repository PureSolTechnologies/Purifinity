import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

@Component({
    selector: 'file-menu',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: '../../html/file/file-menu.html'
})
export class FileMenuComponent {

    private hashId: string;

    constructor(private routeParams: RouteParams) {
        this.hashId = routeParams.get('hashId');
    }

}
