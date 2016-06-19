import {Component, ElementRef, Input} from 'angular2/core';

import {AbstractChartComponent} from './AbstractChartComponent';

@Component({
    selector: 'cummulative-distribution-chart',
    directives: [],
    template:
`<div class="panel panel-default cummulative-distribution-chart">
    <div class="panel-heading">
        <img src="/images/icons/FatCow_Icons16x16/chart_line.png" />
        {{title}}
        <span style="float: right;">
            <a class="btn-default" (click)="exportChart()">
                <img src="/images/icons/FatCow_Icons16x16/download.png" />
            </a>
            <a class="btn-default" (click)="exportChartData()">
                <img src="/images/icons/FatCow_Icons16x16/document_export.png" />
            </a>
            <a class="btn-default" (click)="printChart()">
                <img src="/images/icons/FatCow_Icons16x16/printer_color.png" />
            </a> 
        </span>
    </div>
</div>`
})
export class CummulativeDistributionChartComponent extends AbstractChartComponent {

    @Input()
    private title: string = "Cummulative Distribution Chart";

    constructor(element: ElementRef) { super(element); }

    render(): void { }

}
