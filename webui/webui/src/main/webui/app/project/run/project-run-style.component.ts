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
import {CodeRangeType} from '../../commons/domain/CodeRangeType';
import {IssuesTableComponent} from '../../components/evaluation/issues-table.component';

@Component({
    selector: 'project-run-style',
    directives: [
        ProjectRunMenuComponent,
        CategoryBarChartComponent,
        IssuesTableComponent
    ],
    templateUrl: '../../html/project/run/style-issues.html'
})
export class ProjectRunStyleComponent {

    private projectId: string;
    private runId: string;

    public issueSeverityCount: CategoryChartData = new CategoryChartData();
    public issueParameterCount: CategoryChartData = new CategoryChartData();
    public issues: SingleIssue[] = [];

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
            },
            function(response: Response) { }
        );
    }

}
