import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, Router} from 'angular2/router';

import {AuthenticationData} from './commons/auth/AuthenticationData';
import {AuthenticationService} from './commons/auth/AuthenticationService';

@Component({
    selector: 'login',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/login.html'
})
export class LoginComponent {

    email: string = undefined;
    password: string = undefined;

    private router: Router;
    private authenticationService: AuthenticationService
    private authId = undefined;
    private authData = new AuthenticationData("", "");

    constructor(router: Router, authenticationService: AuthenticationService) {
        this.router = router;
        this.authenticationService = authenticationService;
    }

    login() {
        if (this.email.indexOf("@") <= -1) {
            var authSettings = PurifinityConfiguration.authentication;
            if (authSettings.defaultDomain) {
                this.email = this.email + authSettings.defaultDomain;
            }
        }
        this.authenticationService.login(this.email, this.password);
    };

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['route']);
    };

    isLoggedIn() {
        return this.authenticationService.isLoggedIn();
    };


}
