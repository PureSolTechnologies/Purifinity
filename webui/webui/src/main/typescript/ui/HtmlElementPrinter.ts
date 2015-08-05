/**
 * This class is used to open selected Elements of an HTML document into a new
 * window for separated printing.
*/
class HtmlElementPrinter {

    constructor(private rootElement: Element) {
    }

    public print() {
        var newWindow: Window = window.open();
        var newDocument: Document = newWindow.document;
        var newBody: HTMLElement = newDocument.body;
        var newNode: Node = newDocument.importNode(this.rootElement, true);
        newBody.appendChild(newNode);
        newDocument.close();
        newWindow.print();
        newWindow.close();
    }

}