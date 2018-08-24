import {Component} from 'angular2/core';

import {ReferenceComponent} from './commons/reference.component';

@Component({
    selector: 'documentation',
    directives: [ReferenceComponent],
    templateUrl: '../html/documentation.html'
})
export class DocumentationComponent {
}
