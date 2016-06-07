import {Component, ElementRef, Input} from 'angular2/core';

import {ParetoDatum} from '../../commons/charts/ParetoDatum';
import {HtmlElementPrinter} from '../../commons/HtmlElementPrinter';
import {ChartExport} from '../../commons/ChartExport';

@Component({
    selector: 'pareto-chart',
    directives: [],
    template:
`<div class="panel pareto-chart" (window:resize)="onResize($event)">
    <div class="panel-heading">
        <img src="/images/icons/FatCow_Icons16x16/chart_column_up.png" />
        Pareto Chart
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
export class ParetoChartComponent {

    @Input()
    private data: ParetoDatum[];

    private svg: d3.Selection<any>;
    private margin: number;

    constructor(private element: ElementRef) { }

    ngOnInit() {
        this.svg = d3.select(this.element.nativeElement)
            .append("svg")
            .attr("class", "chart")
            .style("width", "100%");

        this.margin = parseInt(this.element.nativeElement.style.margin, 10) || 30;
    }

    // watch for data changes and re-render
    ngOnChanges(changes) {
        if (this.svg) {
            this.render();
        }
    }

    // Browser onresize event
    onResize(event): void {
        this.render();
    };

    render(): void {
                        this.svg.selectAll("*").remove();
                        if (!this.data) {
                            return;
                        }
                        // setup variables
                        let node: any = d3.select(this.element.nativeElement).node();
                        var width = node.parentNode.offsetWidth - 2 * this.margin;
                        if (width <= 0) {
                            return;
                        }
                        // calculate the height
                        var height = Math.round(width / 2);
                        var barWidth = width / this.data.length;

                        var max = d3.max(this.data, function(d) {
                            return d.value;
                        });
                        var min = d3.min(this.data, function(d) {
                            return d.value;
                        });

                        var color = d3.scale.linear<string,number>()
                            .domain([min, (max - min) / 2, max])
                            .range(["red", "yellow", "green"]);
                        // our scales
                        var xScale = d3.scale.ordinal()
                            .domain(this.data.map(function(d) { return d.name; }))
                            .rangeBands([0, width]);
                        var yScale = d3.scale.linear()
                            .domain([0, max])
                            .range([height, 0]);

                        // set the height based on the calculations above
                        var chart = this.svg
                            .attr("width", width + 2 * this.margin)
                            .attr("height", height + 2 * this.margin)
                            .append("g")
                            .attr("transform", "translate(" + this.margin + "," + this.margin + ")");

                        var xAxis = d3.svg.axis()
                            .scale(xScale)
                            .orient("bottom");
                        var yAxis = d3.svg.axis()
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

    printChart() {
        let printer: HtmlElementPrinter = new HtmlElementPrinter(this.svg.node());
        printer.print();
    }

    exportChart() {
        let exporter: ChartExport = new ChartExport(this.svg.node());
        exporter.export();
    }
}