import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';

import {FileMenuComponent} from './file-menu.component';

@Component({
    selector: 'file-summary',
    directives: [
        FileMenuComponent
    ],
    templateUrl: '../../html/file/summary.html'
})
export class FileSummaryComponent {

    private hashId: string;

    constructor(private routeParams: RouteParams) {
        this.hashId = routeParams.get('hashId');
    }

}
