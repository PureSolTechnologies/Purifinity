import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {PureSolTechnologiesComponent} from './components/puresol-technologies.component';

@Component({
    selector: 'footer',
    directives: [
        ROUTER_DIRECTIVES,
        PureSolTechnologiesComponent
   	],
    templateUrl: '../html/footer.html'

})
export class FooterComponent {
}
