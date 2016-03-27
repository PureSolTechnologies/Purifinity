import {Component} from 'angular2/core';
import {Response} from 'angular2/http';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {AdminMenuComponent} from './admin-menu.component';
import {ProgressIndicatorComponent} from '../components/progress-indicator.component';
import {User} from '../commons/auth/User';
import {Role} from '../commons/auth/Role';
import {AccountManager} from '../commons/purifinity/AccountManager';
import {TabComponent} from '../components/tabs/tab.component';
import {TabSetComponent} from '../components/tabs/tabset.component';

@Component({
    selector: 'users-admin',
    directives: [
        ROUTER_DIRECTIVES,
        AdminMenuComponent,
        ProgressIndicatorComponent,
        TabSetComponent,
        TabComponent
    ],
    templateUrl: '../../html/admin/users-admin.html'
})
export class UsersAdminComponent {

    private accountManager: AccountManager;

    private users: User[] = [];
    private roles: Role[] = [];
    private loading: number = 0;

    constructor(accountManager: AccountManager) {
        this.accountManager = accountManager;

        this.updateUsers();
        let userAdminComponent = this;
        this.accountManager.getRoles(//
            function(roles: Role[]) {
                userAdminComponent.roles = roles;
                userAdminComponent.loading++;
            }, //
            function(response: Response) {
                userAdminComponent.loading++;
            });
    }

    isLoading(): boolean {
        return this.loading < 2;
    }

    deleteUser(email: string): void {
        var userAdminComponent = this;
        this.accountManager.deleteAccount(email,
            function(response: Response) {
                userAdminComponent.updateUsers();
            },
            function(response: Response) {
            }
        );
    }

    updateUsers(): void {
        let userAdminComponent = this;
        this.accountManager.getUsers(//
            function(users: User[]) {
                userAdminComponent.users = users;
                userAdminComponent.loading++;
            }, //
            function(response: Response) {
                userAdminComponent.loading++;
            });
    }
}
