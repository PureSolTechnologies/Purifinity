import {Component} from 'angular2/core';

import {PanelComponent} from '../panel.component';

@Component({
    selector: 'vertical-pareto-chart',
    directives: [
        PanelComponent
    ],
    template:
    `<div class="panel vertical-pareto-chart">
    <div class="panel-heading">
        <img src="/images/icons/FatCow_Icons16x16/chart_column_horizont.png" />
        Vertical Pareto Chart 
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
export class VerticalParetoChartComponent {
}
