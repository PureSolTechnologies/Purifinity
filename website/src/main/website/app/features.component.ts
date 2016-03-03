import {Component} from 'angular2/core';

import {PureSolTechnologiesComponent} from './commons/puresol-technologies.component';
import {PurifinityComponent} from './commons/purifinity.component';

@Component({
	selector: 'features',
	directives: [
	  PureSolTechnologiesComponent,
	  PurifinityComponent
	],
	templateUrl: '../html/features.html'
})
export class FeaturesComponent {
}
