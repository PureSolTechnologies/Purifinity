import {Component} from 'angular2/core';

import {PureSolTechnologiesComponent} from './commons/puresol-technologies.component';
import {PurifinityComponent} from './commons/purifinity.component';

@Component({
	selector: 'about',
	directives: [
	  PureSolTechnologiesComponent,
	  PurifinityComponent
	],
	templateUrl: '../html/about.html'
})
export class AboutComponent {
}
