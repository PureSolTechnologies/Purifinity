import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {ReferenceComponent} from './commons/reference.component';
import {TwitterPurifinityTimelineComponent} from './twitter-purifinity-timeline.component';

@Component({
    selector: 'home',
    directives: [
        TwitterPurifinityTimelineComponent,
        ReferenceComponent,
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/home.html'
})
export class HomeComponent {
}
