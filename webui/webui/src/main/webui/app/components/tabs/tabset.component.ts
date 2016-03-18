import {Component, Input} from 'angular2/core';

import {TabComponent} from './tab.component';

@Component({
    selector: 'tabset',
    directives: [],
    template:
    `<div>
    <ul class="nav nav-tabs">
        <li class="nav-item" *ngFor="#tab of tabs">
            <a class="{{tab.getClass()}}" (click)="selectTab(tab)">{{tab.getHeading()}}</a>
        </li>
    </ul>
    <ng-content></ng-content>
</div>`
})
export class TabSetComponent {

    tabs: TabComponent[] = [];

    addTab(tab: TabComponent) {
        if (this.tabs.length === 0) {
            tab.active = true;
        }
        this.tabs.push(tab);
    }

    selectTab(tab: TabComponent) {
        this.tabs.forEach((tab) => {
            tab.active = false;
        });
        tab.active = true;
    }
}
