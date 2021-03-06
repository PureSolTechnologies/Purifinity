import {Component} from 'angular2/core';
import {RouteParams, Router} from 'angular2/router';
import {Response} from 'angular2/http';

import {FsSizePipe} from '../../commons/pipes/fs-size.pipe';
import {DefaultDatePipe} from '../../commons/pipes/default-date.pipe';
import {DurationPipe} from '../../commons/pipes/duration.pipe';
import {Project} from '../../commons/domain/Project';
import {ProjectRunMenuComponent} from './project-run-menu.component';
import {ProjectManager} from '../../commons/purifinity/ProjectManager';
import {ProgressIndicatorComponent} from '../../components/progress-indicator.component';
import {EvaluatorStore} from '../../commons/purifinity/EvaluatorStore';
import {CategoryBarChartComponent} from '../../components/charts/category-bar-chart.component';
import {CategoryChartData} from '../../components/charts/CategoryChartData';
import {CategoryDatum} from '../../components/charts/CategoryDatum';
import {Utilities} from '../../commons/Utilities';
import {Severity} from '../../commons/domain/Severity';
import {Classification} from '../../commons/domain/Classification';

@Component({
    selector: 'project-run-summary',
    directives: [
        ProjectRunMenuComponent,
        ProgressIndicatorComponent,
        CategoryBarChartComponent
    ],
    pipes: [
        DefaultDatePipe,
        DurationPipe
    ],
    templateUrl: '../../html/project/run/summary.html'
})
export class ProjectRunSummaryComponent {

    private fsSizePipe: FsSizePipe = new FsSizePipe();

    private projectId: string;
    private runId: string;

    private project: Project;
    private run = undefined;
    public issueSeverityCount: CategoryChartData = new CategoryChartData();
    public issueTypeCount: CategoryChartData = new CategoryChartData();

    constructor(private routeParams: RouteParams,
        private router: Router,
        private projectManager: ProjectManager,
        private evaluatorStore: EvaluatorStore) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');

        let component = this;
        projectManager.getProject(this.projectId, function(project: Project) {
            component.project = project;
            projectManager.getRun(component.projectId, component.runId,
                function(data, status) {
                    component.run = data;
                },
                function(response: Response) { });
        }, function(response: Response) {
        });
        evaluatorStore.getRunIssueSummaryBySeverity(this.projectId, this.runId,
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
        evaluatorStore.getRunIssueSummaryByClassification(this.projectId, this.runId,
            function(data: any) {
                component.issueTypeCount = new CategoryChartData();
                for (let name of Utilities.getEnumNames(Classification)) {
                    let value = data[name];
                    if (!value) {
                        value = 0;
                    }
                    let onClick: () => void = null;
                    if (name == "STYLE_ISSUE") {
                        onClick = (): void => { component.router.navigate(['ProjectRunStyle', { projectId: component.projectId, runId: component.runId }]); };
                    } else if (name == "DEFECT") {
                        onClick = (): void => { component.router.navigate(['ProjectRunDefects', { projectId: component.projectId, runId: component.runId }]); };
                    } else if (name == "ARCHITECTURE_ISSUE") {
                        onClick = (): void => { component.router.navigate(['ProjectRunArchitecture', { projectId: component.projectId, runId: component.runId }]); };
                    } else if (name == "DESIGN_ISSUE") {
                        onClick = (): void => { component.router.navigate(['ProjectRunDesign', { projectId: component.projectId, runId: component.runId }]); };
                    }
                    component.issueTypeCount.data.push(new CategoryDatum(name, value, onClick));
                }
            },
            function(response: Response) { }
        );
    }

    isLoading(): boolean {
        return !(this.project && this.run);
    }
}
