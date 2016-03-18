import {Component, Input} from 'angular2/core';

import {TabSetComponent} from './tabset.component';

@Component({
    selector: 'tab',
    directives: [],
    template:
    `<div [hidden]="!active">
    <ng-content></ng-content>
</div>`
})
export class TabComponent {

    @Input() heading: string;
    active: boolean = false;

    constructor(tabs: TabSetComponent) {
        tabs.addTab(this);
    }

    getHeading(): string {
        return this.heading;
    }

    getClass(): string {
        if (this.active) {
            return "nav-link active";
        } else {
            return "nav-link";
        }
    }
}
