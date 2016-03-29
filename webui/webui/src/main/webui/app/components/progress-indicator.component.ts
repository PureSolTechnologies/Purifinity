import {Component, Input} from 'angular2/core';

declare var $: any;

@Component({
    selector: 'progress-indicator',
    directives: [],
    template:
    `<img src="/images/ventilator.gif" width="32px" height="32px"/> Loading...<br/><progress class="progress indicator"></progress>`
})
export class ProgressIndicatorComponent {

    ngAfterContentInit() {
    $('.indicator').css('width', '100%');
}

} 
