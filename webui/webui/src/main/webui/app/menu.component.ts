import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

@Component({
    selector: 'main-menu',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/menu.html'
})
export class MenuComponent {
}
