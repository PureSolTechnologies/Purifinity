import {Component, NgZone} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {PanelComponent} from '../../components/panel.component';

@Component({
    selector: 'logged-in-users',
    directives: [
        ROUTER_DIRECTIVES,
        PanelComponent
    ],
    templateUrl: '../../../html/components/monitor/logged-in-users.html'
})
export class LoggedInUsersComponent {
}
