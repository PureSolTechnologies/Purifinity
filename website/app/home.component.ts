import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {TwitterTimelineComponent} from './twitter-timeline.component';
import {TwitterPurifinityTimelineComponent} from './twitter-purifinity-timeline.component';

@Component({
	selector: 'home',
	directives: [
	  TwitterTimelineComponent,
	  TwitterPurifinityTimelineComponent,
	  ROUTER_DIRECTIVES
	],
	template:
`<div class="container">
  <div class="row">
  </div>
  <div class="row">
    <div class="col-md-6">
      <h1>Introduction</h1>
      <p>
        Purifinity is a source code analysis tool.
      </p>
    </div>
    <div class="col-md-6">
      <h2>News</h2>
      <twitter-purifinity-timeline></twitter-purifinity-timeline>
    </div>
  </div>
</div>
`
})
export class HomeComponent {
}
