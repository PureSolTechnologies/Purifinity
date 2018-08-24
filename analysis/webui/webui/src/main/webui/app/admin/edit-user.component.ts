import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES, Router, RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {AdminMenuComponent} from './admin-menu.component';
import {User} from '../commons/auth/User';
import {Role} from '../commons/auth/Role';
import {AccountManager} from '../commons/purifinity/AccountManager';

@Component({
    selector: 'edit-user',
    directives: [
        ROUTER_DIRECTIVES,
        AdminMenuComponent
    ],
    templateUrl: '../../html/admin/edit-user.html'
})
export class EditUserComponent {

    private router: Router;
    private routeParams: RouteParams
    private accountManager: AccountManager;

    private roles: Role[] = [];
    email: string = "";
    name: string = "";
    roleId: string = "";

    constructor(router: Router, routeParams: RouteParams, accountManager: AccountManager) {
        this.router = router;
        this.routeParams = routeParams;
        this.accountManager = accountManager;

        this.email = routeParams.get('email');

        var userAdminComponent = this;
        this.accountManager.getRoles(//
            function(roles: Role[]) { userAdminComponent.roles = roles }, //
            function(response: Response) { });
        this.accountManager.getUser(this.email, //
            function(user: User) {
                userAdminComponent.name = user.name;
                userAdminComponent.roleId = user.role.id;
            }, //
            function(response: Response) { });
    }

    editUser() {
        var editUserComponent = this;
        this.accountManager.editAccount(this.email, this.name, this.roleId,
            function(response: Response) {
                editUserComponent.router.navigate(['/UsersAdmin']);
            },
            function(response: Response) {
            }
        );
    };

}