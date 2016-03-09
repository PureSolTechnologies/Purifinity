import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {SystemHealthComponent} from './components/monitor/system-health.component';
import {LoggedInUsersComponent} from './components/monitor/logged-in-users.component';
import {RunningJobsComponent} from './components/monitor/running-jobs.component';

@Component({
    selector: 'home',
    directives: [
        ROUTER_DIRECTIVES,
        SystemHealthComponent,
        LoggedInUsersComponent,
        RunningJobsComponent
    ],
    templateUrl: '../html/home.html'
})
export class HomeComponent {
}
