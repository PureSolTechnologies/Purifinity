import {Component} from 'angular2/core';

@Component({
    selector: 'tree-map',
    directives: [],
    template:
`<div class="panel panel-default tree-map-chart">
    <div class="panel-heading">
        <img src="/images/icons/FatCow_Icons16x16/map.png" />
        Tree Map
        <span style="float: right;">
            <a class="btn-default" (click)="exportChart()">
                <img src="/images/icons/FatCow_Icons16x16/download.png" />
            </a>
            <a class="btn-default" (click)="printChart()">
                <img src="/images/icons/FatCow_Icons16x16/printer_color.png" />
            </a>
        </span>
    </div>
</div>`
})
export class TreeMapComponent {
}
