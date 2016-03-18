import {Component} from 'angular2/core';

import {PanelComponent} from '../components/panel.component';
import {TabComponent} from '../components/tabs/tab.component';
import {TabSetComponent} from '../components/tabs/tabset.component';

@Component({
    selector: 'edit-project',
    directives: [
        PanelComponent,
        TabComponent,
        TabSetComponent
    ],
    templateUrl: '../../html/admin/edit-project.html'
})
export class EditProjectComponent { }
