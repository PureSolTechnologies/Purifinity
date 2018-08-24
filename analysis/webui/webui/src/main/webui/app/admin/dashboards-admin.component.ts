import {Component} from 'angular2/core';

import {AdminMenuComponent} from './admin-menu.component';

@Component({
    selector: 'dashboards-admin',
    directives: [
        AdminMenuComponent
    ],
    templateUrl: '../../html/admin/dashboards-admin.html'
})
export class DashboardsAdminComponent { }
