import {TableCell} from './TableCell';

/**
  * This class represents a tree or node of a tree table tree.
 */
export class TreeTableNode {

    children: Array<TreeTableNode> = new Array<TreeTableNode>();
    id: string;
    content: string;
    imageUrl: string;
    link: string;
    routerLink: Array<any>;
    columns: Array<TableCell> = new Array<TableCell>();

    constructor(public parent: TreeTableNode) {
    }

    public addChild(child: TreeTableNode) {
        this.children.push(child);
        child.parent = this;
    }

    public addColumn(column: TableCell) {
        this.columns.push(column);
    }
}
