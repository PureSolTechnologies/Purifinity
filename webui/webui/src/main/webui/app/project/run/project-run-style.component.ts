import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {Utilities} from '../../commons/Utilities';
import {Severity} from '../../commons/domain/Severity';
import {EvaluatorStore} from '../../commons/purifinity/EvaluatorStore';
import {ProjectRunMenuComponent} from './project-run-menu.component';
import {CategoryBarChartComponent} from '../../components/charts/category-bar-chart.component';
import {CategoryChartData} from '../../components/charts/CategoryChartData';
import {CategoryDatum} from '../../components/charts/CategoryDatum';

@Component({
    selector: 'project-run-style',
    directives: [
        ProjectRunMenuComponent,
        CategoryBarChartComponent
    ],
    templateUrl: '../../html/project/run/style-issues.html'
})
export class ProjectRunStyleComponent {

    private projectId: string;
    private runId: string;

    public issueSeverityCount: CategoryChartData = new CategoryChartData();

    constructor(private routeParams: RouteParams, private evaluatorStore: EvaluatorStore) {
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
    }

}
