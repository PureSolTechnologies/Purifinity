import {Component, ElementRef, Input} from 'angular2/core';

import {ParetoDatum} from './ParetoDatum';
import {HtmlElementPrinter} from '../../commons/HtmlElementPrinter';
import {ChartExport} from '../../commons/ChartExport';

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
export class VerticalParetoChartComponent {

    @Input()
    private data: ParetoDatum[];

    @Input()
    private title:string = "Vertical Pareto Chart";

    private svg: d3.Selection<any>;
    private margin: number;
    private barHeight: number;
    private barPadding: number;


    constructor(private element: ElementRef) { }

    ngOnInit() {
        this.svg = d3.select(this.element.nativeElement)
            .append("svg")
            .attr("class", "chart")
            .style("width", "100%");

        this.margin = parseInt(this.element.nativeElement.style.margin, 10) || 20;
        this.barHeight = parseInt(this.element.nativeElement.style.barHeight, 10) || 20;
        this.barPadding = parseInt(this.element.nativeElement.style.barPadding, 10) || 5;
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
        let component = this;
        // setup variables
        let node: any = d3.select(this.element.nativeElement).node();
        let width = node.parentNode.offsetWidth - this.margin;
        if (width <= 0) {
            return;
        }
        // calculate the height
        let height = this.data.length * (this.barHeight + this.barPadding);

        let max = d3.max(this.data, function(d: any) {
            return d.value;
        });
        let min = d3.min(this.data, function(d: any) {
            return d.value;
        });
        let color = d3.scale.linear<string,number>()
            .domain([min, (max - min) / 2, max])
            .range(["red", "yellow", "green"]);
        // our xScale
        let xScale = d3.scale.linear()
            .domain([0, max])
            .range([0, width]);

        // set the height based on the calculations above
        this.svg.attr("width", width);
        this.svg.attr("height", height);
        //create the rectangles for the bar chart
        this.svg.selectAll("rect")
            .data(this.data)
            .enter()
            .append("rect")
            .on("click", function(d, i) {
                return this.onClick({ item: d });
            })
            .attr("height", this.barHeight)
            .attr("width", 140)
            .attr("x", Math.round(this.margin / 2))
            .attr("y", function(d, i) {
                return i * (component.barHeight + component.barPadding);
            })
            .attr("fill", function(d: any) { return color(d.value); })
            .transition()
            .duration(1000)
            .attr("width", function(d: any) {
                return xScale(d.value);
            });
        this.svg.selectAll("text")
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

    printChart() {
        let printer: HtmlElementPrinter = new HtmlElementPrinter(this.svg.node());
        printer.print();
    }

    exportChart() {
        let exporter: ChartExport = new ChartExport(this.svg.node());
        exporter.export();
    }

}
