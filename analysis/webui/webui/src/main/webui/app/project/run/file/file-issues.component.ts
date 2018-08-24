import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {FileStore} from '../../../commons/purifinity/FileStore';
import {EvaluatorStore} from '../../../commons/purifinity/EvaluatorStore';
import {FileMenuComponent} from './file-menu.component';
import {SingleIssue} from '../../../commons/domain/issues/SingleIssue';
import {Table} from '../../../commons/tables/Table';
import {TableCell} from '../../../commons/tables/TableCell';
import {TableRow} from '../../../commons/tables/TableRow';
import {TableColumnHeader} from '../../../commons/tables/TableColumnHeader';
import {CodeRangeType} from '../../../commons/domain/CodeRangeType';
import {IssuesTableComponent} from '../../../components/evaluation/issues-table.component';

@Component({
    selector: 'file-issues',
    directives: [
        FileMenuComponent,
        IssuesTableComponent
    ],
    templateUrl: '../../../../html/project/run/file/issues.html'
})
export class FileIssuesComponent {

    private projectId: string;
    private runId: string;
    private hashId: string;
    private issues: SingleIssue[] = [];

    constructor(private routeParams: RouteParams, private fileStore: FileStore, private evaluatorStore: EvaluatorStore) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');
        this.hashId = routeParams.get('hashId');

        let component = this;
        evaluatorStore.getFileIssues(this.projectId, this.runId, this.hashId,
            function(data: SingleIssue[]) {
                component.issues = data;
            },
            function(response: Response) { }
        );
    }

}
