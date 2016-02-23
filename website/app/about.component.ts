import {Component} from 'angular2/core';

import {PureSolTechnologiesComponent} from './puresol-technologies.component';
import {PurifinityComponent} from './purifinity.component';

@Component({
	selector: 'about',
	directives: [
	  PureSolTechnologiesComponent,
	  PurifinityComponent
	],
	template:
`<div class="row">
  <div class="col-md-12">
    <h1>About</h1>
    <p>
      To be done...
    </p>
  </div>
</div>`
})
export class AboutComponent {
}
