import {Component, ElementRef, Input} from 'angular2/core';

import {HtmlElementPrinter} from '../../commons/HtmlElementPrinter';
import {ChartExport} from '../../commons/ChartExport';

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
export class CategoryBarChartComponent {

    @Input()
    private data: CategoryChartData;

    @Input()
    private title:string = "Category Bar Chart";

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
        // remove all previous items before render
        this.svg.selectAll("*").remove();
        if (!this.data) {
            return;
        }
                        // setup variables
                        let data = this.data;
                        if (data.values.length != data.categories.length) {
                            return;
                        }
                        let node: any = d3.select(this.element.nativeElement).node();
                        var width = node.parentNode.offsetWidth - 2 * this.margin;
                        if (width <= 0) {
                            return;
                        }
                        // calculate the height
                        var height = Math.round(width / 2) - 2 * this.margin;
                        // bins and bars
                        var barCount = data.categories.length;
                        var barWidth = width / barCount;


                        var binData: number[] = [];
                        var bins: string[] = [];
                        for (var i = 0; i < data.categories.length; i++) {
                            bins.push(data.categories[i]);
                            binData.push(data.values[i]);
                        }

                        var max = d3.max(binData, function(d) {
                            return d;
                        });
                        var min = d3.min(binData, function(d) {
                            return d;
                        });

                        var color = d3.scale.linear<string,number>()
                            .domain([min, (max - min) / 2, max])
                            .range(["red", "yellow", "green"]);
                        // our yScale
                        var xScale = d3.scale.ordinal()
                            .domain(bins);
                        var yScale = d3.scale.linear()
                            .domain([0, max])
                            .range([0, height]);

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

    printChart() {
        let printer: HtmlElementPrinter = new HtmlElementPrinter(this.svg.node());
        printer.print();
    }

    exportChart() {
        let exporter: ChartExport = new ChartExport(this.svg.node());
        exporter.export();
    }
}

export class CategoryChartData {

    public categories: string[] = [];
    public values: number[] = [];

}