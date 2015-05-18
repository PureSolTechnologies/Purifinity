class TreeTableTree {

    parent: TreeTableTree;
    children: TreeTableTree[];
    id: string;
    content: string;
    imageUrl: string;
    link: string;
    columns: TreeTableColumn[];

    public addChild(child: TreeTableTree) {
        this.children.push(child);
        child.parent = this;
    }

    public addColumn(column: TreeTableColumn) {
        this.columns.push(column);
    }
}
