import {Component, ElementRef, Input} from 'angular2/core';

import {AbstractChartComponent} from './AbstractChartComponent';
import {CategoryChartData} from './CategoryChartData';
import {CategoryDatum} from './CategoryDatum';

@Component({
    selector: 'category-bar-chart',
    directives: [],
    template:
    `<div class="panel category-bar-chart" (window:resize)="onResize($event)">
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
export class CategoryBarChartComponent extends AbstractChartComponent {

    @Input()
    private data: CategoryChartData;

    @Input()
    private title: string = "Category Bar Chart";

    constructor(element: ElementRef) { super(element); }

    render(): void {
        // data
        if (!this.data) {
            return;
        }
        let data = this.data;
        // geometry
        let width = this.getWidth();
        if (width <= 0) {
            return;
        }
        let height = this.getHeight();
        let margin = this.getMargin();
        // bins and bars
        let barCount = data.data.length;
        let barWidth = width / barCount;


        let binData: number[] = [];
        let bins: string[] = [];
        for (let i = 0; i < data.data.length; i++) {
            let datum: CategoryDatum = data.data[i]
            bins.push(datum.getName());
            binData.push(datum.getValue());
        }

        let max = d3.max(binData);
        let min = d3.min(binData);

        let color = d3.scale.linear<string, number>()
            .domain([min, (max - min) / 2, max])
            .range(["red", "yellow", "green"]);
        // our yScale
        let xScale = d3.scale.ordinal()
            .domain(bins).rangeBands([0, width]);;
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
            .data(binData)
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
            .attr("fill", function(d) { return color(d); })
            .transition()
            .duration(1000)
            .attr("y", function(d) {
                return yScale(d);
            })
            .attr("height", function(d) {
                return height - yScale(d);
            });
    }
}
