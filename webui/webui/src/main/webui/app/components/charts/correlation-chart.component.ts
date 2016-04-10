import {Component} from 'angular2/core';

@Component({
    selector: 'correlation-chart',
    directives: [],
    template:
    `<div class="panel correlation-chart">
    <div class="panel-heading">
        <img src="/images/icons/FatCow_Icons16x16/chart_bullseye.png" />
        Correlation Chart
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
export class CorrelationChartComponent {
}
