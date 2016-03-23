import {Component} from 'angular2/core';

import {TabComponent} from '../components/tabs/tab.component';
import {TabSetComponent} from '../components/tabs/tabset.component';

import {ConfigurationComponent} from '../components/preferences/configuration.component';

@Component({
    selector: 'settings-admin',
    directives: [
        TabSetComponent,
        TabComponent,
        ConfigurationComponent
    ],
    templateUrl: '../../html/admin/settings-admin.html'
})
export class SettingsAdminComponent { }
