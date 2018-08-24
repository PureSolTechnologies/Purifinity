import {Component} from 'angular2/core';

import {ReferenceComponent} from './commons/reference.component';

@Component({
	selector: 'contribute',
    directives: [ReferenceComponent],
	templateUrl: '../html/contribute.html'
})
export class ContributeComponent {
}
