class Tree<T> {

    parent: Tree<T>;
    children: Tree<T>[];

    constructor(content: T) {
    }

    addChild(child: Tree<T>) {
        this.children.push(child);
        child.parent = this;
    }

}