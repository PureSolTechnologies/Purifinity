import {Component, Input} from 'angular2/core';

@Component({
    selector: 'help',
    directives: [],
    template:
`<div class="inline-help">
    <a href="/manuals/purifinity-user.html#{{ref}}" target="purifinity-help">
        <img src="/images/icons/FatCow_Icons16x16/help.png"/>
    </a>
</div>`    
})
export class HelpComponent {

    @Input() ref: string;

}
