import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    selector: 'help',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/help.html'
})
export class HelpComponent {
}
