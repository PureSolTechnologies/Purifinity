import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, Router} from 'angular2/router';
import {Response} from 'angular2/http';

import {MenuComponent} from './menu.component';
import {AccountManager} from './commons/purifinity/AccountManager';
import {AuthenticationService} from './commons/auth/AuthenticationService';

@Component({
    selector: 'change-password',
    directives: [
        ROUTER_DIRECTIVES,
        MenuComponent
    ],
    templateUrl: '../html/change-password.html'
})
export class ChangePasswordComponent {

    private accountManager: AccountManager;
    private router: Router;
    private email: string;
    private oldPassword: string;
    private newPassword: string;
    private newPassword2: string;

    constructor(router: Router, accountManager: AccountManager, authService: AuthenticationService) {
        this.router = router;
        this.accountManager = accountManager;
        this.email = authService.authData.authId;
    }

    getEmail(): string {
        if (this.email) {
            return this.email;
        }
        return "";
    }


    changePassword() {
        this.accountManager.changePassword(this.email, this.oldPassword, this.newPassword,
            function(response: Response) {
            },
            function(response: Response) {
            }
        );
        this.router.navigate(['/Account']);
    };

    disableOK(): boolean {
        if (!this.oldPassword) {
            return true;
        }
        if (!this.newPassword) {
            return true;
        }
        if (!this.newPassword2) {
            return true;
        }
        if (this.newPassword !== this.newPassword2) {
            return true;
        }
        return false;
    }

}
