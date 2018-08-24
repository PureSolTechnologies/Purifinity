import {Component} from 'angular2/core';

import {PureSolTechnologiesComponent} from './commons/puresol-technologies.component';
import {PurifinityComponent} from './commons/purifinity.component';
import {ReferenceComponent} from './commons/reference.component';

@Component({
	selector: 'features',
	directives: [
	  PureSolTechnologiesComponent,
      ReferenceComponent,
	  PurifinityComponent
	],
	templateUrl: '../html/features.html'
})
export class FeaturesComponent {
}
