import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';
import {Response} from 'angular2/http';

import {MenuComponent} from './menu.component';
import {AccountManager} from './commons/purifinity/AccountManager';
import {AuthenticationService} from './commons/auth/AuthenticationService';
import {Role} from './commons/auth/Role';
import {User} from './commons/auth/User';

@Component({
    selector: 'account',
    directives: [
        ROUTER_DIRECTIVES,
        MenuComponent
    ],
    templateUrl: '../html/account.html'
})
export class AccountComponent {

    private email: string;;
    private authId: string;
    private authToken: string;
    private user: User;

    constructor(accountManager: AccountManager, authService: AuthenticationService) {
        this.email = authService.authData.authId;
        this.authId = authService.authData.authId;
        this.authToken = authService.authData.authToken;
        var ac = this;
        accountManager.getUser(this.email,
            function(user: User) { 
                ac.user = user; 
        }, function(response: Response) { });
    }

    getUserName(): string {
        if (this.user && this.user.name) {
            return this.user.name;
        }
        return "";
    }

    getUserEmail(): string {
        if (this.user && this.user.email) {
            return this.user.email;
        }
        return "";
    }

    getRoleName(): string {
        if (this.user && this.user.role && this.user.role.name) {
            return this.user.role.name;
        }
        return "";
    }

    getAuthId(): string {
        if (this.authId) {
            return this.authId;
        }
        return "";
    }
    
    getAuthToken(): string {
        if (this.authToken) {
            return this.authToken;
        }
        return "";
    }

}
