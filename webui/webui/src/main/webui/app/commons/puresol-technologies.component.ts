import {Component} from 'angular2/core';

/**
 * This small component generates a link with PureSol Technologies logo
 * to the PureSol Technologies' homepage.
 */
@Component({
    selector: 'puresol-technologies',
    template: `<a href="http://puresol-technologies.com"><span class="logo"><span class="puresol">PureSol</span> <span class="technologies">Technologies</span></span></a>`
})
export class PureSolTechnologiesComponent {
}
