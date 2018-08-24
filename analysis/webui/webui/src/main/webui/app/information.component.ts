import {Component} from 'angular2/core';
import {Response} from 'angular2/http';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {Utilities} from './commons/Utilities';
import {MenuComponent} from './menu.component';
import {SystemHealthComponent} from './components/monitor/system-health.component';
import {LoggedInUsersComponent} from './components/monitor/logged-in-users.component';
import {RunningJobsComponent} from './components/monitor/running-jobs.component';
import {TabComponent} from './components/tabs/tab.component';
import {TabSetComponent} from './components/tabs/tabset.component';

import {PluginManager} from './commons/purifinity/PluginManager';
import {AnalyzerServiceInformation} from './commons/plugins/AnalyzerServiceInformation';
import {EvaluatorServiceInformation} from './commons/plugins/EvaluatorServiceInformation';
import {RepositoryServiceInformation} from './commons/plugins/RepositoryServiceInformation';

@Component({
    selector: 'information',
    directives: [
        ROUTER_DIRECTIVES,
        TabSetComponent,
        TabComponent,
        MenuComponent,
        SystemHealthComponent,
        LoggedInUsersComponent,
        RunningJobsComponent
    ],
    templateUrl: '../html/information.html'
})
export class InformationComponent {

    analyzers: AnalyzerServiceInformation[] = [];
    evaluators: EvaluatorServiceInformation[] = [];
    repositories: RepositoryServiceInformation[] = [];

    constructor(private pluginManager: PluginManager) {
        let component = this;
        pluginManager.getAnalyzers(
            function(data: AnalyzerServiceInformation[]) {
                component.analyzers = data;
                component.analyzers.sort(
                    function(left, right) {
                        return Utilities.strcmp(left.name, right.name);
                    });
            },
            function(response: Response) { }
        );
        pluginManager.getEvaluators(
            function(data: EvaluatorServiceInformation[]) {
                component.evaluators = data;
                component.evaluators.sort(
                    function(left, right) {
                        return Utilities.strcmp(left.name, right.name);
                    });
            },
            function(response: Response) { }
        );
        pluginManager.getRepositoryTypes(
            function(data: RepositoryServiceInformation[]) {
                component.repositories = data;
                component.repositories.sort(
                    function(left, right) {
                        return Utilities.strcmp(left.name, right.name);
                    });
            },
            function(response: Response) { }
        );
    }
}
