import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {AuthenticationService} from './commons/auth/AuthenticationService';

@Component({
    selector: 'header',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: '../html/header.html'
})
export class HeaderComponent {

    private authenticationService: AuthenticationService;

    constructor(authenticationService: AuthenticationService) {
        this.authenticationService = authenticationService;
    }

    isLoggedIn(): boolean {
        return this.authenticationService.isLoggedIn();
    }

    logout() {
        this.authenticationService.logout();
    }
}
