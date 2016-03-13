import {Component, Input} from 'angular2/core';
import {Response} from 'angular2/http';

import {DefaultDatePipe} from '../commons/pipes/default-date.pipe';
import {ProjectManager} from '../commons/purifinity/ProjectManager';
import {PanelComponent} from './panel.component';

@Component({
    selector: 'project-list',
    directives: [PanelComponent],
    pipes: [DefaultDatePipe],
    templateUrl: '../../html/components/project-list.html'
})
export class ProjectListComponent {

    private projectManager: ProjectManager;
    private projects = [];

    constructor(projectManager: ProjectManager) {
        this.projectManager = projectManager;
        var projectListComponent = this;
        projectManager.getProjects(//
            function(data, status) {
                projectListComponent.projects = data;
                if (!projectListComponent.projects) {
                    projectListComponent.projects = [];
                }
            }, //
            function(response: Response) {
                projectListComponent.projects = [];
            }
        );
    }

    triggerNewRun(id) {
        this.projectManager.triggerNewRun(id,
            function(data, status) { },
            function(response: Response) { }
        );
    }

}
