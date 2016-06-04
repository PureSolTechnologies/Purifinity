import {Component} from 'angular2/core';
import {Response} from 'angular2/http';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

import {EvaluatorServiceInformation} from './commons/plugins/EvaluatorServiceInformation';
import {PluginManager} from './commons/purifinity/PluginManager';

@Component({
    selector: 'evaluator-plugin',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../html/evaluator-plugin.html'
})
export class EvaluatorPluginComponent {

    pluginId: string;
    pluginName: string;
    evaluator: EvaluatorServiceInformation;

    constructor(routeParams: RouteParams, pluginManager: PluginManager) {
        this.pluginId = routeParams.get('pluginId');
        this.pluginName = this.pluginId;
        let component = this;
        pluginManager.getEvaluator(this.pluginId,
            function(data: EvaluatorServiceInformation) {
                component.evaluator = data;
                component.pluginName = data.name;
            },
            function(response: Response) { });
    }
}
