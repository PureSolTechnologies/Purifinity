import {Component, ElementRef, Input} from 'angular2/core';

import {AbstractChartComponent} from './AbstractChartComponent';

@Component({
    selector: 'correlation-chart',
    directives: [],
    template:
    `<div class="chart correlation-chart">
        <img src="/images/icons/FatCow_Icons16x16/chart_bullseye.png" />
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
</div>`
})
export class CorrelationChartComponent extends AbstractChartComponent {

    @Input()
    private title: string = "Correlation Chart";

    constructor(element: ElementRef) { super(element); }

    render(): void { }

}
