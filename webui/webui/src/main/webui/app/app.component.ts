import {Component} from 'angular2/core';
import {bootstrap} from 'angular2/platform/browser';
import {
    ROUTER_DIRECTIVES,
    RouteConfig
} from 'angular2/router';

import {HeaderComponent} from './header.component';
import {FooterComponent} from './footer.component';
// Main Application
import {HomeComponent} from './home.component';
import {LoginComponent} from './login.component';
import {AccountComponent} from './account.component';
import {ChangePasswordComponent} from './change-password.component';
import {DashboardsComponent} from './dashboards.component';
import {InformationComponent} from './information.component';
import {HelpComponent} from './help.component';
import {LicenseComponent} from './license.component';
// Administration backend
import {DashboardsAdminComponent} from './admin/dashboards-admin.component';
import {ProjectsAdminComponent} from './admin/projects-admin.component';
import {CreateProjectComponent} from './admin/create-project.component';
import {EditProjectComponent} from './admin/edit-project.component';
import {SettingsAdminComponent} from './admin/settings-admin.component';
import {UsersAdminComponent} from './admin/users-admin.component';
import {BackendAdminComponent} from './admin/backend-admin.component';
import {CreateUserComponent} from './admin/create-user.component';
import {EditUserComponent} from './admin/edit-user.component';
// Project pages
import {ProjectSummaryComponent} from './project/project-summary.component';
import {ProjectTrendsComponent} from './project/project-trends.component';
import {ProjectRunsComponent} from './project/project-runs.component';

/**
 * This component is Purifinity's central application.
 */
@Component({
    selector: 'app',
    directives: [
        HeaderComponent,
        FooterComponent,
        ROUTER_DIRECTIVES
    ],
    template:
    `<header></header>
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
    { path: '/admin/projects/:projectId/edit', name: 'EditProject', component: EditProjectComponent },
    { path: '/admin/settings', name: 'SettingsAdmin', component: SettingsAdminComponent },
    { path: '/admin/users', name: 'UsersAdmin', component: UsersAdminComponent },
    { path: '/admin/users/create', name: 'CreateUser', component: CreateUserComponent },
    { path: '/admin/users/:email/edit', name: 'EditUser', component: EditUserComponent },
    { path: '/admin/backend', name: 'BackendAdmin', component: BackendAdminComponent },
    // Project pages
    { path: '/projects/:projectId/summary', name: 'ProjectSummary', component: ProjectSummaryComponent },
    { path: '/projects/:projectId/trends', name: 'ProjectTrends', component: ProjectTrendsComponent },
    { path: '/projects/:projectId/runs', name: 'ProjectRuns', component: ProjectRunsComponent },
])
export class AppComponent {}
