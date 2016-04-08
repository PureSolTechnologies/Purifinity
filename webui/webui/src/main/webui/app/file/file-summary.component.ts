import {Component} from 'angular2/core';
import {Response} from 'angular2/http';
import {RouteParams} from 'angular2/router';

import {FileMenuComponent} from './file-menu.component';
import {FileStore} from '../commons/purifinity/FileStore';
import {DefaultDatePipe} from '../commons/pipes/default-date.pipe';
import {DurationPipe} from '../commons/pipes/duration.pipe';
import {VersionPipe} from '../commons/pipes/version.pipe';
import {SourceCodeComponent} from '../components/source-code.component';

@Component({
    selector: 'file-summary',
    directives: [
        FileMenuComponent,
        SourceCodeComponent
    ],
    pipes: [
        DefaultDatePipe,
        DurationPipe,
        VersionPipe
    ],
    templateUrl: '../../html/file/summary.html'
})
export class FileSummaryComponent {

    private hashId: string;
    private analyses: any[] = [];
    private sourceCode: any = {};

    constructor(private routeParams: RouteParams, private fileStore: FileStore) {
        this.hashId = routeParams.get('hashId');

        let component = this;
        fileStore.wasAnalyzed(this.hashId, function(data, status) {
            if (data) {
                fileStore.loadAnalyses(component.hashId, function(data: any[]) {
                    component.analyses = data;
                }, function(response: Response) {
                });
            }
        }, function(response: Response) {
        });
        fileStore.readSourceCode(this.hashId, function(data, status) {
            component.sourceCode = data.json();
        }, function(response: Response) {
        });
    }
}
