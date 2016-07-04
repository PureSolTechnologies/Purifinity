import {Component} from 'angular2/core';
import {RouteParams, Router} from 'angular2/router';
import {Response} from 'angular2/http';

import {Utilities} from '../../commons/Utilities';
import {Severity} from '../../commons/domain/Severity';
import {EvaluatorStore} from '../../commons/purifinity/EvaluatorStore';
import {ProjectRunMenuComponent} from './project-run-menu.component';
import {CategoryBarChartComponent} from '../../components/charts/category-bar-chart.component';
import {CategoryChartData} from '../../components/charts/CategoryChartData';
import {CategoryDatum} from '../../components/charts/CategoryDatum';
import {DataTableComponent} from '../../components/tables/data-table.component';
import {SingleIssue} from '../../commons/domain/issues/SingleIssue';
import {Table} from '../../commons/tables/Table';
import {TableCell} from '../../commons/tables/TableCell';
import {TableRow} from '../../commons/tables/TableRow';
import {TableColumnHeader} from '../../commons/tables/TableColumnHeader';
import {CodeRangeType} from '../../commons/domain/CodeRangeType';

@Component({
    selector: 'project-run-style',
    directives: [
        ProjectRunMenuComponent,
        CategoryBarChartComponent,
        DataTableComponent
    ],
    templateUrl: '../../html/project/run/style-issues.html'
})
export class ProjectRunStyleComponent {

    private projectId: string;
    private runId: string;

    public issueSeverityCount: CategoryChartData = new CategoryChartData();
    public issueParameterCount: CategoryChartData = new CategoryChartData();
    public issues: SingleIssue[] = [];
    public tableData: Table = new Table("Style Issues");

    constructor(private routeParams: RouteParams, private router: Router, private evaluatorStore: EvaluatorStore) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');

        let component = this;
        evaluatorStore.getRunIssueStyleSeverities(this.projectId, this.runId,
            function(data: any) {
                component.issueSeverityCount = new CategoryChartData();
                for (let name of Utilities.getEnumNames(Severity)) {
                    let value = data[name];
                    if (!value) {
                        value = 0;
                    }
                    component.issueSeverityCount.data.push(new CategoryDatum(name, value));
                }
            },
            function(response: Response) { }
        );
        evaluatorStore.getRunIssueStyleParameters(this.projectId, this.runId,
            function(data: any) {
                component.issueParameterCount = new CategoryChartData();
                for (let issueId in data) {
                    let value = data[issueId];
                    if (!value) {
                        value = 0;
                    }
                    component.issueParameterCount.data.push(new CategoryDatum(issueId, value));
                }
            },
            function(response: Response) { }
        );
        evaluatorStore.getRunStyleIssues(this.projectId, this.runId,
            function(data: SingleIssue[]) {
                component.issues = data;
                component.tableData = new Table("Style Issues");
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
                    row.addCell(new TableCell(issue.sourceCodeLocation.internalLocation, null,
                        (): void => { component.router.navigate(['/FileIssues', { projectId: component.projectId, runId: component.runId, hashId: issue.hashId.algorithmName + ':' + issue.hashId.hash }]) },
                        issue.languageName + ' ' + issue.languageVersion));
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
