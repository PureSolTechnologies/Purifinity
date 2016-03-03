import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {TwitterPurifinityTimelineComponent} from './twitter-purifinity-timeline.component';

@Component({
	selector: 'home',
	directives: [
	  TwitterPurifinityTimelineComponent,
	  ROUTER_DIRECTIVES
	],
	templateUrl: '../html/home.html'
})
export class HomeComponent {
}
