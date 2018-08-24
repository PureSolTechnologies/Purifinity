import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {MenuComponent} from './menu.component';

@Component({
    selector: 'dashboards',
    directives: [
        ROUTER_DIRECTIVES,
        MenuComponent
    ],
    templateUrl: '../html/dashboards.html'
})
export class DashboardsComponent {
}
