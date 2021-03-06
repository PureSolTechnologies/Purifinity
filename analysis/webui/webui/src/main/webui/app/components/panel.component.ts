import {Component, Input} from 'angular2/core';

@Component({
    selector: 'panel',
    directives: [],
    template:
    `<div class="panel">
  <div class="panel-heading">
    <img *ngIf="icon" src="{{icon}}"/> {{title}}
  </div>
  <div class="panel-body">
    <ng-content></ng-content>
  </div>
</div>`
})
export class PanelComponent {

    @Input() title: string;
    @Input() icon: string;

}

