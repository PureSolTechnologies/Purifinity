import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    selector: 'account',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/account.html'
})
export class AccountComponent {
}
