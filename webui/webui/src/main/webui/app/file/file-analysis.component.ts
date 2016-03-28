import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';

import {FileMenuComponent} from './file-menu.component';

@Component({
    selector: 'file-analysis',
    directives: [
        FileMenuComponent
    ],
    templateUrl: '../../html/file/analysis.html'
})
export class FileAnalysisComponent {

    private hashId: string;

    constructor(private routeParams: RouteParams) {
        this.hashId = routeParams.get('hashId');
    }

}
