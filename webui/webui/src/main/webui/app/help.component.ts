import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {MenuComponent} from './menu.component';

@Component({
    selector: 'help',
    directives: [
        ROUTER_DIRECTIVES,
        MenuComponent
    ],
    templateUrl: '../html/help.html'
})
export class HelpComponent {
}
