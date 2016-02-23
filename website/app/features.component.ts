import {Component} from 'angular2/core';

import {PureSolTechnologiesComponent} from './puresol-technologies.component';
import {PurifinityComponent} from './purifinity.component';

@Component({
	selector: 'features',
	directives: [
	  PureSolTechnologiesComponent,
	  PurifinityComponent
	],
	template:
`<div class="container">
  <div class="row">
    <h1>Features</h1>
    <p>
      To be done...
    </p>
  </div>
</div>`
})
export class FeaturesComponent {
}
