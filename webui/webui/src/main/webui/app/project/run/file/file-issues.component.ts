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
import {DataTableComponent} from '../../../components/tables/data-table.component';

@Component({
    selector: 'file-issues',
    directives: [
        FileMenuComponent,
        DataTableComponent
    ],
    templateUrl: '../../../../html/project/run/file/issues.html'
})
export class FileIssuesComponent {

    private projectId: string;
    private runId: string;
    private hashId: string;
    private issues: SingleIssue[] = [];
    private tableData = new Table("Issues");

    constructor(private routeParams: RouteParams, private fileStore: FileStore, private evaluatorStore: EvaluatorStore) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');
        this.hashId = routeParams.get('hashId');

        let component = this;
        evaluatorStore.getFileIssues(this.projectId, this.runId, this.hashId,
            function(data: SingleIssue[]) {
                component.issues = data;
                component.tableData = new Table("Issues");
                component.tableData.addColumn(new TableColumnHeader("Severity", ""));
                component.tableData.addColumn(new TableColumnHeader("Classification", ""));
                component.tableData.addColumn(new TableColumnHeader("File", ""));
                component.tableData.addColumn(new TableColumnHeader("Code Range Name", ""));
                component.tableData.addColumn(new TableColumnHeader("Parameter Name", ""));
                component.tableData.addColumn(new TableColumnHeader("Description", ""));
                for (let issue of data) {
                    let row = new TableRow(component.tableData.getColumns());
                    row.addCell(new TableCell(issue.severity));
                    row.addCell(new TableCell(issue.classification));
                    row.addCell(new TableCell(issue.sourceCodeLocation.internalLocation, null, null, issue.languageName + ' ' + issue.languageVersion));
                    row.addCell(new TableCell(issue.codeRangeName, null, null, CodeRangeType[issue.codeRangeType]));
                    row.addCell(new TableCell(issue.parameter.name));
                    row.addCell(new TableCell(issue.parameter.description));
                    component.tableData.addRow(row);
                }
            },
            function(response: Response) { }
        );
    }

}
