import {Component, Input} from 'angular2/core';

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
        
        constructor(projectManager:ProjectManager) {
            this.projectManager = projectManager;
            var projects = this.projects;
            projectManager.getProjects(//
                function(data, status) {
                    projects = data;
                    if (!projects) {
                    projects = [];
                    }
                }, //
                function(data, status, error) { }
            );
        }
        
        triggerNewRun(id) {
            this.projectManager.triggerNewRun(id,
                function(data, status) { },
                function(data, status, error) { }
                );
        }
            
}
