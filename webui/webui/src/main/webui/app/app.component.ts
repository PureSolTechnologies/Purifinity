import {Component} from 'angular2/core';
import {bootstrap} from 'angular2/platform/browser';
import {
    ROUTER_DIRECTIVES,
    Location,
    RouteConfig
} from 'angular2/router';

import {HeaderComponent} from './header.component';
import {FooterComponent} from './footer.component';
// Main Application
import {MenuComponent} from './menu.component';
import {HomeComponent} from './home.component';
import {LoginComponent} from './login.component';
import {AccountComponent} from './account.component';
import {ChangePasswordComponent} from './change-password.component';
import {DashboardsComponent} from './dashboards.component';
import {InformationComponent} from './information.component';
import {HelpComponent} from './help.component';
import {LicenseComponent} from './license.component';
// Administration backend
import {AdminMenuComponent} from './admin/admin-menu.component';
import {DashboardsAdminComponent} from './admin/dashboards-admin.component';
import {ProjectsAdminComponent} from './admin/projects-admin.component';
import {CreateProjectComponent} from './admin/create-project.component';
import {EditProjectComponent} from './admin/edit-project.component';
import {SettingsAdminComponent} from './admin/settings-admin.component';
import {UsersAdminComponent} from './admin/users-admin.component';
import {BackendAdminComponent} from './admin/backend-admin.component';

/**
 * This component is Purifinity's central application.
 */
@Component({
    selector: 'app',
    directives: [
        MenuComponent,
        AdminMenuComponent,
        HeaderComponent,
        FooterComponent,
        ROUTER_DIRECTIVES
    ],
    template:
    `<header></header>
<menu *ngIf="showMainMenu()"></menu>
<admin-menu *ngIf="showAdminMenu()"></admin-menu>
<alerter></alerter>
<router-outlet></router-outlet>
<footer></footer>`
})
@RouteConfig([
    // Main Application
    { path: '/', name: 'Home', component: HomeComponent, useAsDefault: true },
    { path: '/login', name: 'Login', component: LoginComponent },
    { path: '/account', name: 'Account', component: AccountComponent },
    { path: '/change-password', name: 'ChangePassword', component: ChangePasswordComponent },
    { path: '/dashboards', name: 'Dashboards', component: DashboardsComponent },
    { path: '/information', name: 'Information', component: InformationComponent },
    { path: '/help', name: 'Help', component: HelpComponent },
    { path: '/license', name: 'License', component: LicenseComponent },
    // Administration Backend
    { path: '/admin', name: 'Admin', redirectTo: ['/DashboardsAdmin'] },
    { path: '/admin/dashboards', name: 'DashboardsAdmin', component: DashboardsAdminComponent },
    { path: '/admin/projects', name: 'ProjectsAdmin', component: ProjectsAdminComponent },
    { path: '/admin/projects/create', name: 'CreateProject', component: CreateProjectComponent },
    { path: '/admin/projects/:projectId', name: 'EditProject', component: EditProjectComponent },
    { path: '/admin/settings', name: 'SettingsAdmin', component: SettingsAdminComponent },
    { path: '/admin/users', name: 'UsersAdmin', component: UsersAdminComponent },
    { path: '/admin/backend', name: 'BackendAdmin', component: BackendAdminComponent },
])
export class AppComponent {

    private location: Location;

    constructor(location: Location) {
        this.location = location;
    }

    showMainMenu(): boolean {
        return (!this.showAdminMenu());
    }

    showAdminMenu(): boolean {
        let path: string = this.location.path();
        return path.indexOf("/admin/") >= 0;
    }

}
