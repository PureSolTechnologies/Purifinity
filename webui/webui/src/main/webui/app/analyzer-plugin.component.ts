import {Component} from 'angular2/core';
import {Response} from 'angular2/http';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

import {AnalyzerServiceInformation} from './commons/plugins/AnalyzerServiceInformation';

@Component({
    selector: 'analyzer-plugin',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/analyzer-plugin.html'
})
export class AnalyzerPluginComponent {

    public pluginId: string;
    public pluginInformation: AnalyzerServiceInformation;

    constructor(routeParams: RouteParams) {
        this.pluginId = routeParams.get('pluginId');
    }

}
