import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {MenuComponent} from './menu.component';

@Component({
    selector: 'license',
    directives: [
        ROUTER_DIRECTIVES,
        MenuComponent
    ],
    templateUrl: '../html/license.html'
})
export class LicenseComponent {
}
