import {TableCell} from '../tables/TableCell';

/**
  * This class represents a tree or node of a tree table tree.
 */
export class TreeTableTree {

    children: TreeTableTree[];
    id: string;
    content: string;
    imageUrl: string;
    link: string;
    columns: TableCell[] = [];

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
