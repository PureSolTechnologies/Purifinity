import {Component, NgZone} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {PanelComponent} from '../../components/panel.component';

@Component({
    selector: 'configuration-component',
    directives: [
        PanelComponent
    ],
    templateUrl: '../../../html/components/preferences/configuration-component.html'
})
export class ConfigurationComponent {
}
