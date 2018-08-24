import {Component, Output, EventEmitter} from 'angular2/core';
import {Response} from 'angular2/http';

import {PluginManager} from '../../commons/purifinity/PluginManager';
import {EvaluatorServiceInformation} from '../../commons/plugins/EvaluatorServiceInformation';

/**
 * This component is a simple selector which reads the installed evaluator from REST and lets the user select
 * a evaluator. 
 */
@Component({
    selector: 'evaluator-selection',
    template:
    `<select 
        id="evaluatorSelector" 
        class="selectpicker show-tick form-control" 
        title="Evaluator Selector" 
        [(ngModel)]="selectedEvaluatorId" 
        (ngModelChange)="update()">
    <option value="">Select an evaluator</option>
    <option *ngFor="#evaluator of evaluators" value="{{evaluator.id}}">
        {{evaluator.name}}
    </option>
</select>`
})
export class EvaluatorSelectionComponent {

    @Output('evaluator')
    private evaluatorEmitter = new EventEmitter<EvaluatorServiceInformation>();
 
    private selectedEvaluatorId:  string = "";
    private evaluators:  Array<EvaluatorServiceInformation> = [];

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

    update()  {
        for (let evaluator of this.evaluators) {
            if (this.selectedEvaluatorId === evaluator.id) {
                this.evaluatorEmitter.emit(evaluator);
            }
        }
    }
}
