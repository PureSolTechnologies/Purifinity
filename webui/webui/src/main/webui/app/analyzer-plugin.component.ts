import {Component} from 'angular2/core';
import {Response} from 'angular2/http';
import {ROUTER_DIRECTIVES, RouteParams} from 'angular2/router';

import {AnalyzerServiceInformation} from './commons/plugins/AnalyzerServiceInformation';
import {GrammarViewComponent} from './components/grammar/grammar-view.component';
import {PluginManager} from './commons/purifinity/PluginManager';

@Component({
    selector: 'analyzer-plugin',
    directives: [
        ROUTER_DIRECTIVES,
        GrammarViewComponent
    ],
    templateUrl: '../html/analyzer-plugin.html'
})
export class AnalyzerPluginComponent {

    pluginId: string;
    pluginName: string;
    pluginInformation: AnalyzerServiceInformation;
    analyzer: AnalyzerServiceInformation;
    grammar: string;

    constructor(routeParams: RouteParams, pluginManager: PluginManager) {
        this.pluginId = routeParams.get('pluginId');
        this.pluginName = this.pluginId;
        let component = this;
        pluginManager.getAnalyzer(this.pluginId,
            function(data: AnalyzerServiceInformation) {
                component.analyzer = data;
                component.pluginName = data.name;
            },
            function(response: Response) { });
        pluginManager.getAnalyzerGrammar(this.pluginId,
            function(data: string) {
                component.grammar = data;
            },
            function(response: Response) { });
    }

}
