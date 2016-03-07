import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    selector: 'login',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/login.html'
})
export class LoginComponent {
}
