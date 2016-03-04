import {Component, Input} from 'angular2/core';

@Component({
    selector: 'ref',
    template: `<a href="{{url}}" target="_blank"><ng-content></ng-content></a>`
})
export class ReferenceComponent {
        
  @Input() url: String;
  @Input() target: String;

}
