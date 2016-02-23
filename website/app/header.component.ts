import {Component} from 'angular2/core';

import {PureSolTechnologiesComponent} from './puresol-technologies.component';
import {PurifinityComponent} from './purifinity.component';

@Component({
	selector: 'header',
	directives: [
	  PureSolTechnologiesComponent,
	  PurifinityComponent
	],
	template:
`<div class="navbar">
  <div class="col-sm-6">
    <a class="navbar-brand" href="#"><purifinity style="font-size:18pt;"></purifinity></a>
  </div>
  <div class="col-sm-6" style="text-align:right;">
    <span style="font-size:16pt;"><puresol-technologies></puresol-technologies></span>
  </div>
</div>`  
})
export class HeaderComponent {
}
