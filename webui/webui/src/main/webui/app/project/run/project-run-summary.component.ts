import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {FsSizePipe} from '../../commons/pipes/fs-size.pipe';
import {DefaultDatePipe} from '../../commons/pipes/default-date.pipe';
import {DurationPipe} from '../../commons/pipes/duration.pipe';
import {Project} from '../../commons/domain/Project';
import {ProjectRunMenuComponent} from './project-run-menu.component';
import {ProjectManager} from '../../commons/purifinity/ProjectManager';
import {ProgressIndicatorComponent} from '../../components/progress-indicator.component';

@Component({
    selector: 'project-run-summary',
    directives: [
        ProjectRunMenuComponent,
        ProgressIndicatorComponent
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

    constructor(private routeParams: RouteParams, private projectManager: ProjectManager) {
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
    }

    isLoading(): boolean {
        return !(this.project && this.run);
    }
}
