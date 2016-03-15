import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, Router} from 'angular2/router';
import {Response} from 'angular2/http';

import {Role} from '../commons/auth/Role';
import {AccountManager} from '../commons/purifinity/AccountManager';

@Component({
    selector: 'edit-user',
    directives: [ROUTER_DIRECTIVES],
    templateUrl: '../../html/admin/edit-user.html'
})
export class EditUserComponent {

    private router: Router;
    private accountManager: AccountManager;

    email: string = "";
    name: string = "";
    roleId: string = "";
    private roles: Role[] = [];

    constructor(router: Router, accountManager: AccountManager) {
        this.router = router;
        this.accountManager = accountManager;

        var userAdminComponent = this;
        this.accountManager.getRoles(//
            function(roles: Role[]) { userAdminComponent.roles = roles }, //
            function(response: Response) { });
    }

    editUser() {
        var editUserComponent = this;
        this.accountManager.editAccount(this.email, this.name, this.roleId,
            function(response: Response) {
                editUserComponent.router.navigate['/UsersAdmin'];
            },
            function(response: Response) {
            }
        );
    };

}