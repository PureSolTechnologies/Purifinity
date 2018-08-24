import {Component, Input} from 'angular2/core';
import {RouteParams, Router} from 'angular2/router';
import {DataTableComponent} from '../tables/data-table.component';

import {Table} from '../../commons/tables/Table';
import {TableCell} from '../../commons/tables/TableCell';
import {TableRow} from '../../commons/tables/TableRow';
import {TableColumnHeader} from '../../commons/tables/TableColumnHeader';
import {CodeRangeType} from '../../commons/domain/CodeRangeType';
import {SingleIssue} from '../../commons/domain/issues/SingleIssue';

@Component({
    selector: 'issues-table',
    directives: [DataTableComponent],
    template: `<data-table [tableData]="tableData"></data-table>`
})
export class IssuesTableComponent {

    @Input()
    private issues: SingleIssue[];

    @Input()
    private title: string = "Issues";

    private projectId: string;
    private runId: string;
    private tableData: Table;

    constructor(private routeParams: RouteParams, private router: Router) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');
    }

    ngOnInit() {
    }

    ngOnChanges(changes) {
        this.tableData = new Table(this.title);
        this.tableData.addColumn(new TableColumnHeader("Severity", ""));
        this.tableData.addColumn(new TableColumnHeader("Classification", ""));
        this.tableData.addColumn(new TableColumnHeader("File", ""));
        this.tableData.addColumn(new TableColumnHeader("Code Range Name", ""));
        this.tableData.addColumn(new TableColumnHeader("Parameter Name", ""));
        this.tableData.addColumn(new TableColumnHeader("Description", ""));
        for (let issue of this.issues) {
            let row = new TableRow(this.tableData.getColumns());
            row.addCell(new TableCell(issue.severity));
            row.addCell(new TableCell(issue.classification));
            if (this.projectId && this.runId) {
                row.addCell(new TableCell(issue.sourceCodeLocation.internalLocation, null,
                    (): void => { this.router.navigate(['/FileIssues', { projectId: this.projectId, runId: this.runId, hashId: issue.hashId.algorithmName + ':' + issue.hashId.hash }]) },
                    issue.languageName + ' ' + issue.languageVersion));
            } else {
                row.addCell(new TableCell(issue.sourceCodeLocation.internalLocation, null,
                    null, issue.languageName + ' ' + issue.languageVersion));
            }
            row.addCell(new TableCell(issue.codeRangeName, null, null, CodeRangeType[issue.codeRangeType]));
            row.addCell(new TableCell(issue.parameter.name));
            row.addCell(new TableCell(issue.parameter.description));
            this.tableData.addRow(row);
        }
    }
}
