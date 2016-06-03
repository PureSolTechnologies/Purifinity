import {Component} from 'angular2/core';
import {Response} from 'angular2/http';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

@Component({
    selector: 'evaluator-plugin',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/evaluator-plugin.html'
})
export class EvaluatorPluginComponent {

    pluginId: string;

    constructor(routeParams: RouteParams) {
        this.pluginId = routeParams.get('pluginId');
    }
}
