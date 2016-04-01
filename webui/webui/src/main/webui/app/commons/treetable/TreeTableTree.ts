import {TableCell} from '../tables/TableCell';

/**
  * This class represents a tree or node of a tree table tree.
 */
export class TreeTableTree {

    children: Array<TreeTableTree> = new Array<TreeTableTree>();
    id: string;
    content: string;
    imageUrl: string;
    link: string;
    routerLink: Array<any>;
    columns: Array<TableCell> = new Array<TableCell>();

    constructor(public parent: TreeTableTree) {
    }

    public addChild(child: TreeTableTree) {
        this.children.push(child);
        child.parent = this;
    }

    public addColumn(column: TableCell) {
        this.columns.push(column);
    }
}
