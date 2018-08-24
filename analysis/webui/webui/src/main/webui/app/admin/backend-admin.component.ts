import {Component} from 'angular2/core';

import {AdminMenuComponent} from './admin-menu.component';
import {TabComponent} from '../components/tabs/tab.component';
import {TabSetComponent} from '../components/tabs/tabset.component';

@Component({
    selector: 'backend-admin',
    directives: [
        AdminMenuComponent,
        TabSetComponent, 
        TabComponent
    ],
    templateUrl: '../../html/admin/backend-admin.html'
})
export class BackendAdminComponent { }
