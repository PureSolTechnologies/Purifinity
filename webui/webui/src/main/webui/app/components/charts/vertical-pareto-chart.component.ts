import {Component, ElementRef, Input} from 'angular2/core';

import {AbstractChartComponent} from './AbstractChartComponent';
import {ParetoDatum} from './ParetoDatum';

@Component({
    selector: 'vertical-pareto-chart',
    directives: [],
    template:
    `<div class="panel vertical-pareto-chart" (window:resize)="onResize($event)">
    <div class="panel-heading">
        <img src="/images/icons/FatCow_Icons16x16/chart_column_horizont.png" />
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
export class VerticalParetoChartComponent extends AbstractChartComponent {

    @Input()
    private data: ParetoDatum[];

    @Input()
    private title: string = "Vertical Pareto Chart";

    private barHeight: number;
    private barPadding: number;

    constructor(element: ElementRef) { super(element); }

    ngOnInit() {
        super.ngOnInit();
        this.barHeight = parseInt(this.getElement().nativeElement.style.barHeight, 10) || 20;
        this.barPadding = parseInt(this.getElement().nativeElement.style.barPadding, 10) || 5;
    }

    render(): void {
        if (!this.data) {
            return;
        }
        let component = this;
        // geometry
        let width = this.getWidth();
        if (width <= 0) {
            return;
        }
        let height = this.data.length * (this.barHeight + this.barPadding);
        let margin = this.getMargin();

        let max = d3.max(this.data, function(d: any) {
            return d.value;
        });
        let min = d3.min(this.data, function(d: any) {
            return d.value;
        });
        let color = d3.scale.linear<string, number>()
            .domain([min, (max - min) / 2, max])
            .range(["red", "yellow", "green"]);
        // our xScale
        let xScale = d3.scale.linear()
            .domain([0, max])
            .range([0, width]);

        // set the height based on the calculations above
        let svg = this.getSVG();
        svg.attr("width", width);
        svg.attr("height", height);
        //create the rectangles for the bar chart
        svg.selectAll("rect")
            .data(this.data)
            .enter()
            .append("rect")
            .on("click", function(d, i) {
                return this.onClick({ item: d });
            })
            .attr("height", this.barHeight)
            .attr("width", 140)
            .attr("x", Math.round(margin / 2))
            .attr("y", function(d, i) {
                return i * (component.barHeight + component.barPadding);
            })
            .attr("fill", function(d: any) { return color(d.value); })
            .transition()
            .duration(1000)
            .attr("width", function(d: any) {
                return xScale(d.value);
            });
        svg.selectAll("text")
            .data(this.data)
            .enter()
            .append("text")
            .on("click", function(d, i) {
                return this.onClick({ item: d });
            })
            .attr("fill", "#000")
            .attr("y", function(d, i) {
                return i * (component.barHeight + component.barPadding) + 15;
            })
            .attr("x", 15)
            .text(function(d: any) {
                return d.name + " (" + String((Math.round(d.value * 100) / 100).toFixed(2)) + ")";
            });
    }
}
