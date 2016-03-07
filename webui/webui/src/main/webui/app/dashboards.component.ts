import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    selector: 'dashboards',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/dashboards.html'
})
export class DashboardsComponent {
}
