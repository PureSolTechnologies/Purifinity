import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, Router} from 'angular2/router';
import {Response} from 'angular2/http';

import {AdminMenuComponent} from './admin-menu.component';
import {Role} from '../commons/auth/Role';
import {AccountManager} from '../commons/purifinity/AccountManager';

@Component({
    selector: 'create-user',
    directives: [
        AdminMenuComponent,
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../../html/admin/create-user.html'
})
export class CreateUserComponent {

    public name: string = "";
    public email: string = "";
    public password: string = "";
    public password2: string = "";
    public roleId: string = "";
    private roles: Role[] = [];

    constructor(private router: Router, private accountManager: AccountManager) {
        var createUserComponent = this;
        this.accountManager.getRoles(//
            function(roles: Role[]) { createUserComponent.roles = roles }, //
            function(response: Response) { });
    }

    createUser() {
        var createUserComponent = this;
        this.accountManager.createAccount(this.email, this.name, this.password, this.roleId,
            function(response: Response) {
                createUserComponent.router.navigate(['/UsersAdmin']);
            },
            function(response: Response) {
            }
        );
    };

    disableOK(): boolean {
        if (!this.password) {
            return true;
        }
        if (!this.password2) {
            return true;
        }
        if (this.password !== this.password2) {
            return true;
        }
        if (!this.email) {
            return true;
        }
        if (!this.name) {
            return true;
        }
        return false;
    }
}
