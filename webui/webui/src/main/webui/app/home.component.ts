import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {SystemHealthComponent} from './components/monitor/system-health.component';

@Component({
    selector: 'home',
    directives: [
        ROUTER_DIRECTIVES,
        SystemHealthComponent
    ],
    templateUrl: '../html/home.html'
})
export class HomeComponent {
}
