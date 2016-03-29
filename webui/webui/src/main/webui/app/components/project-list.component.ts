import {Component, Input} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';
import {Response} from 'angular2/http';

import {ProgressIndicatorComponent} from './progress-indicator.component';
import {Project} from '../commons/domain/Project';
import {DefaultDatePipe} from '../commons/pipes/default-date.pipe';
import {ProjectManager} from '../commons/purifinity/ProjectManager';
import {PanelComponent} from './panel.component';

@Component({
    selector: 'project-list',
    directives: [
        ROUTER_DIRECTIVES,
        PanelComponent,
        ProgressIndicatorComponent
    ],
    pipes: [DefaultDatePipe],
    templateUrl: '../../html/components/project-list.html'
})
export class ProjectListComponent {

    private projects: Project[] = [];

    constructor(private projectManager: ProjectManager) {
        let projectListComponent = this;
        projectManager.getProjects(
            function(projects: Project[]): void {
                projectListComponent.projects = projects;
                if (!projectListComponent.projects) {
                    projectListComponent.projects = [];
                }
            },
            function(response: Response): void {
                projectListComponent.projects = [];
            }
        );
    }

    triggerNewRun(id: string): void {
        this.projectManager.triggerNewRun(id,
            function() { },
            function(response: Response) { }
        );
    }

    isLoading(): boolean {
        return this.projects.length === 0;
    }
}
