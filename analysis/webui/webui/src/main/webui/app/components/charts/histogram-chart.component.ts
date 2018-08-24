import {Component, ElementRef, Input} from 'angular2/core';

import {AbstractChartComponent} from './AbstractChartComponent';

@Component({
    selector: 'histogram-chart',
    directives: [],
    template:
    `<div class="chart histogram-chart" (window:resize)="onResize($event)">
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
</div>`
})
export class HistogramChartComponent extends AbstractChartComponent {

    @Input()
    private data: any;

    @Input()
    private title: string = "Histogram Chart";

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
        // bins and bars
        let binCount = Math.floor(Math.sqrt(this.data.length));
        let barWidth = width / binCount;

        let dataMax = d3.max(this.data, function(d: any) {
            return d.value;
        });
        let dataMin = d3.min(this.data, function(d: any) {
            return d.value;
        });

        let binData = new Array(binCount);
        let bins = new Array(binCount);
        for (let i = 0; i < binData.length; i++) {
            binData[i] = 0;
            bins[i] = String((Math.round((dataMin + (dataMax - dataMin) / binCount * (i + 0.5)) * 100) / 100).toFixed(2));
        }
        for (let key in this.data) {
            let value = this.data[key].value;
            let binNum = Math.round((value - dataMin) / (dataMax - dataMin) * binCount);
            binData[binNum < binCount ? binNum : binCount - 1]++;
        }

        let max = d3.max(binData, function(d) {
            return d;
        });
        let min = d3.min(binData, function(d) {
            return d;
        });

        let color = d3.scale.linear<string, number>()
            .domain([min, (max - min) / 2, max])
            .range(["red", "yellow", "green"]);
        // our yScale
        let xScale = d3.scale.ordinal()
            .domain(bins)
            .rangeBands([0, width]);
        let yScale = d3.scale.linear()
            .domain([0, max])
            .range([0, height]);

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
                return height - yScale(d);
            })
            .attr("height", function(d) {
                return yScale(d);
            });
    }
}
