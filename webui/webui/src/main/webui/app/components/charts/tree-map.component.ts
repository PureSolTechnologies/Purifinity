import {Component, ElementRef, Input} from 'angular2/core';

import {HtmlElementPrinter} from '../../commons/HtmlElementPrinter';
import {ChartExport} from '../../commons/ChartExport';

@Component({
    selector: 'tree-map',
    directives: [],
    template:
    `<div class="panel tree-map-chart">
    <div class="panel-heading">
        <img src="/images/icons/FatCow_Icons16x16/map.png" />
        Tree Map
        <span style="float: right;">
            <a class="btn-default" (click)="exportChart()">
                <img src="/images/icons/FatCow_Icons16x16/download.png" />
            </a>
            <a class="btn-default" (click)="printChart()">
                <img src="/images/icons/FatCow_Icons16x16/printer_color.png" />
            </a>
        </span>
    </div>
</div>`
})
export class TreeMapComponent {

    @Input()
    private data: any;

    private chart: d3.Selection<any>;

    constructor(private element: ElementRef) { }

    ngOnInit() {
        this.chart = d3.select(this.element.nativeElement)
            .append("div")
            .attr("class", "chart")
            .style("width", "100%");
    }

    // watch for data changes and re-render
    ngOnChanges(changes) {
        if (this.chart) {
            this.render();
        }
    }

    // Browser onresize event
    onResize(event): void {
        this.render();
    };

    render(): void {
        // remove all previous items before render
        this.chart.selectAll("*").remove();
        if (!this.data) {
            return;
        }
        // setup variables
        var element0 = this.element.nativeElement;
        var selected = d3.select(element0);
        var node = selected.node();
        var width = node.parentElement.offsetWidth;
        if (width <= 0) {
            return;
        }
        // calculate the height
        var height = Math.round(width / 1.6);

        var color = d3.scale.category20c();

        var treemap = d3.layout.treemap()
            .size([width, height])
            //.sticky(true)
            .value(function(d:any) { return d.size; });

        var div = this.chart.append("div")
            .style("position", "relative")
            .style("width", width + "px")
            .style("height", height + "px")
            .style("left", "0px")
            .style("top", "0px");

        this.position = function() {
            this.style("left", function(d) { return d.x + "px"; })
                .style("top", function(d) { return d.y + "px"; })
                .style("width", function(d) { return Math.max(0, d.dx - 1) + "px"; })
                .style("height", function(d) { return Math.max(0, d.dy - 1) + "px"; });
        };

        div.datum(this.data).selectAll(".node")
            .data(treemap.nodes)
            .enter().append("div")
            .attr("class", "node")
            .call(this.position)
            .style("background", function(d:any) { return d.children ? color(d.name) : null; })
            .text(function(d:any) { return d.children ? null : d.name; });
    }

    position = function() {
        this.style("left", function(d) { return d.x + "px"; })
            .style("top", function(d) { return d.y + "px"; })
            .style("width", function(d) { return Math.max(0, d.dx - 1) + "px"; })
            .style("height", function(d) { return Math.max(0, d.dy - 1) + "px"; });
    }
    
    printChart() {
        let printer: HtmlElementPrinter = new HtmlElementPrinter(this.element.nativeElement.node());
        printer.print();
    }

    exportChart() {
        let exporter: ChartExport = new ChartExport(this.element.nativeElement.node());
        exporter.export();
    }
}

