import {Component} from 'angular2/core';
import {bootstrap} from 'angular2/platform/browser';
import {
    ROUTER_DIRECTIVES,
    RouteConfig
} from 'angular2/router';

import {HeaderComponent} from './header.component';
import {FooterComponent} from './footer.component';
import {MenuComponent} from './menu.component';
import {HomeComponent} from './home.component';
import {LoginComponent} from './login.component';
import {AccountComponent} from './account.component';
import {DashboardsComponent} from './dashboards.component';
import {InformationComponent} from './information.component';
import {HelpComponent} from './help.component';
import {LicenseComponent} from './license.component';

/**
 * This component is Purifinity's central application.
 */
@Component({
    selector: 'app',
    directives: [
        MenuComponent,
        HeaderComponent,
        FooterComponent,
        ROUTER_DIRECTIVES
    ],
    template:
`<header></header>
<menu></menu>
<router-outlet></router-outlet>
<footer></footer>`
})
@RouteConfig([
    { path: '/', name: 'root', redirectTo: ['/Home'] },
    { path: '/home', name: 'Home', component: HomeComponent },
    { path: '/login', name: 'Login', component: LoginComponent },
    { path: '/account', name: 'Account', component: AccountComponent },
    { path: '/dashboards', name: 'Dashboards', component: DashboardsComponent },
    { path: '/information', name: 'Information', component: InformationComponent },
    { path: '/help', name: 'Help', component: HelpComponent },
    { path: '/license', name: 'License', component: LicenseComponent },
])
export class AppComponent {
}
