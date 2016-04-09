import {Component} from 'angular2/core';
import {Response} from 'angular2/http';

import {PluginManager} from '../../commons/purifinity/PluginManager';
import {EvaluatorServiceInformation} from '../../commons/plugins/EvaluatorServiceInformation';

@Component({
    selector: 'panel',
    directives: [],
    template:
    `<select id="evaluatorSelector" title="Evaluator Selector" class="selectpicker show-tick form-control" data-ng-model="evaluatorSelection">
    <option value="">Select an evaluator</option>
    <option *ngFor="#evaluator of evaluators" value="{{evaluator.id}}">
        {{evaluator.name}}
    </option>
</select>`
})
export class EvaluatorSelectionComponent {

    private evaluators: EvaluatorServiceInformation[] = [];

    constructor(private pluginManager: PluginManager) {
        let component = this;
        pluginManager.getEvaluators(
            function(evaluators: EvaluatorServiceInformation[]) {
                component.evaluators = evaluators;
                component.evaluators.sort(function(l, r) { return (l.name < r.name) ? - 1 : ((l.name > r.name) ? 1 : 0); });
            },
            function(response: Response) { }
        );
    }

}