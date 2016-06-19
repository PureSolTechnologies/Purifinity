import {Component, ElementRef, Input} from 'angular2/core';

import {AbstractChartComponent} from './AbstractChartComponent';
import {ParetoDatum} from './ParetoDatum';

@Component({
    selector: 'pareto-chart',
    directives: [],
    template:
    `<div class="panel pareto-chart" (window:resize)="onResize($event)">
    <div class="panel-heading">
        <img src="/images/icons/FatCow_Icons16x16/chart_column_up.png" />
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
export class ParetoChartComponent extends AbstractChartComponent {

    @Input()
    private data: ParetoDatum[];

    @Input()
    private title: string = "Pareto Chart";

    constructor(element: ElementRef) { super(element); }

    render(): void {
        if (!this.data) {
            return;
        }
        // geometry
        let width = this.getWidth();
        if (width <= 0) {
            return;
        }
        let height = this.getHeight();
        let margin = this.getMargin();

        let barWidth = width / this.data.length;

        let max = d3.max(this.data, function(d) {
            return d.value;
        });
        let min = d3.min(this.data, function(d) {
            return d.value;
        });

        let color = d3.scale.linear<string, number>()
            .domain([min, (max - min) / 2, max])
            .range(["red", "yellow", "green"]);
        // our scales
        let xScale = d3.scale.ordinal()
            .domain(this.data.map(function(d) { return d.name; }))
            .rangeBands([0, width]);
        let yScale = d3.scale.linear()
            .domain([0, max])
            .range([height, 0]);

        // set the height based on the calculations above
        let svg = this.getSVG();
        let chart = svg
            .attr("width", width + 2 * margin)
            .attr("height", height + 2 * margin)
            .append("g")
            .attr("transform", "translate(" + margin + "," + margin + ")");

        let xAxis = d3.svg.axis()
            .scale(xScale)
            .orient("bottom");
        let yAxis = d3.svg.axis()
            .scale(yScale)
            .orient("left");
        chart
            .append("g")
            .attr("class", "x axis")
            .attr("transform", "translate(0," + height + ")")
            .call(xAxis);
        chart
            .append("g")
            .attr("class", "y axis")
            .call(yAxis);

        //create the rectangles for the bar chart
        chart.selectAll("rect")
            .data(this.data)
            .enter()
            .append("rect")
            .on("click", function(d, i) {
                return this.onClick({ item: d });
            })
            .attr("height", 0)
            .attr("width", barWidth)
            .attr("x", function(d, i) {
                return i * barWidth;
            })
            .attr("y", height)
            .attr("fill", function(d) { return color(d.value); })
            .transition()
            .duration(1000)
            .attr("y", function(d) {
                return yScale(d.value);
            })
            .attr("height", function(d) {
                return height - yScale(d.value);
            });
    }
}
