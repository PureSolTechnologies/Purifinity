import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {ReferenceComponent} from './commons/reference.component';

@Component({
	selector: 'download',
	directives: [
	  ROUTER_DIRECTIVES,
      ReferenceComponent
	],
	templateUrl: '../html/download.html'
})
export class DownloadComponent {
}
