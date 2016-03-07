import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    selector: 'home',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/home.html'
})
export class HomeComponent {
}
