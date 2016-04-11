/**
 * This class is used to export SVG picture Elements as pixel based image.
 */
export class ChartExport {

    constructor(private svgElement: Node) {
        if ((!svgElement.nodeName) || (svgElement.nodeName !== 'svg')) {
            throw new Error("No SVG provided!");
        }
    }

    public export() {
        var newWindow: Window = window.open();
        var newDocument: Document = newWindow.document;
        var newBody: HTMLElement = newDocument.body;
        var newHead: HTMLElement = newDocument.head;

        var bootstrapCSS: any = newDocument.createElement("link");
        bootstrapCSS.setAttribute("rel", "stylesheet");
        bootstrapCSS.setAttribute("href", "lib/bootstrap/css/bootstrap.min.css");
        newHead.appendChild(bootstrapCSS);

        var purifinityCSS: any = newDocument.createElement("link");
        purifinityCSS.setAttribute("rel", "stylesheet");
        purifinityCSS.setAttribute("href", "css/purifinity.css");
        newHead.appendChild(purifinityCSS);

        var canvas: any = newDocument.createElement("canvas");
        canvas.width = this.svgElement['width'].baseVal.value;
        canvas.height = this.svgElement['height'].baseVal.value;
        canvas.style.border = "1pt solid black";
        newBody.appendChild(canvas);

        var ctx: any = canvas.getContext('2d');
        
        // Load up our image.
        var source: any = new Image();
        // https://developer.mozilla.org/en/XMLSerializer
        var svg_xml: string = (new XMLSerializer()).serializeToString(this.svgElement);
        var mySrc: string = 'data:image/svg+xml;base64,' + window.btoa(svg_xml);
        source.src = mySrc;
        source.width = canvas.width;
        source.height = canvas.heigth;
        // Render our SVG image to the canvas once it loads.
        source.onload = function() {
            ctx.drawImage(source, 0, 0);
        }

        /* 
        var newNode: Node = newDocument.importNode(this.svgElement, true);
        newBody.appendChild(newNode); 
        */
        newDocument.close();
    }
}
