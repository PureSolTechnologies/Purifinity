/**
 * This class represents a tree or node of a tree table tree.
 */
class TreeTableTree {

    children: TreeTableTree[];
    id: string;
    content: string;
    imageUrl: string;
    link: string;
    columns: TreeTableColumn[];

    constructor(public parent: TreeTableTree) {
    }
       
    public addChild(child: TreeTableTree) {
        this.children.push(child);
        child.parent = this;
    }

    public addColumn(column: TreeTableColumn) {
        this.columns.push(column);
    }
}
