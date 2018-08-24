import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {ProjectManager} from '../commons/purifinity/ProjectManager';
import {PanelComponent} from '../components/panel.component';
import {ProjectMenuComponent} from './project-menu.component';
import {DefaultDatePipe} from '../commons/pipes/default-date.pipe';
import {DurationPipe} from '../commons/pipes/duration.pipe';

@Component({
    selector: 'project-summary',
    directives: [
        ROUTER_DIRECTIVES,
        ProjectMenuComponent,
        PanelComponent
    ],
    pipes: [
        DefaultDatePipe,
        DurationPipe
    ],
    templateUrl: '../../html/project/summary.html'
})
export class ProjectSummaryComponent {

    private projectId: string;
    private lastRun: any = {};

    constructor(private routeParams: RouteParams, private projectManager: ProjectManager) {
        let projectSummaryComponent = this;
        this.projectId = routeParams.get('projectId');
        projectManager.getLastRun(this.projectId,
            function(data, status) {
                projectSummaryComponent.lastRun = data;
            },
            function(response: Response) {
            }
        );
    }

}
