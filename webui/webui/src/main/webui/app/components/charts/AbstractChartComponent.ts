import {ElementRef} from 'angular2/core';

import {HtmlElementPrinter} from '../../commons/HtmlElementPrinter';
import {ChartExport} from '../../commons/ChartExport';

/**
 * This class contains all basic functionality to create D3 charts for Purifinity.
 */
export abstract class AbstractChartComponent {

    private chartElement: d3.Selection<any>;
    private svg: d3.Selection<any>;
    private margin: number;

    /**
     * This is the base constructor.
     * @param element is to be injected.
     */
    constructor(private element: ElementRef) { }

    ngOnInit() {
        this.chartElement = d3.select(this.element.nativeElement).select("div.chart");
        this.svg = this.chartElement.append("svg")
            .attr("class", "chart")
            .style("width", "100%");
        let node = this.getNode();
        this.margin = parseInt(node.style.margin, 10) || 30;
    }

    // watch for data changes and re-render
    ngOnChanges(changes) {
        if (this.svg) {
            this.removeOldChart();
            this.render();
        }
    }

    // Browser onresize event
    onResize(event): void {
        this.removeOldChart();
        this.render();
    };

    protected getElement(): ElementRef {
        return this.element;
    }

    protected getSVG(): d3.Selection<any> {
        return this.svg;
    }

    protected getWidth(): number {
        let currentNode = this.getNode();
        while ((currentNode) && (currentNode.offsetWidth <= 0)) {
            currentNode = currentNode.parentNode;
        }
        if (!currentNode) {
            return -1;
        }
        if (!currentNode.offsetWidth) {
            return -1;
        }
        return currentNode.offsetWidth - 2 * this.margin;
    }

    protected getHeight(): number {
        return Math.round(this.getWidth() / 2) - 2 * this.margin;
    }

    protected getMargin(): number {
        return this.margin;
    }

    abstract render(): void;

    protected removeOldChart(): void {
        // remove all previous items before render
        this.svg.selectAll("*").remove();
    }

    protected getNode(): any {
        return this.chartElement.node();
    }

    protected printChart() {
        let printer: HtmlElementPrinter = new HtmlElementPrinter(this.svg.node());
        printer.print();
    }

    protected exportChart() {
        let exporter: ChartExport = new ChartExport(this.svg.node());
        exporter.export();
    }

}