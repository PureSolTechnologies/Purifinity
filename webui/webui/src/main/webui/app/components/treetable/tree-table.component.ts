import {Component, Input} from 'angular2/core';

import {TreeTableData} from '../../commons/treetable/TreeTableData';
import {TreeTableTree} from '../../commons/treetable/TreeTableTree';

@Component({
    selector: 'tree-table',
    directives: [],
    templateUrl: '../../../html/components/treetable/tree-table.html'
})
export class TreeTableComponent {

    @Input()
    private treeTableData: TreeTableData;

    path: TreeTableTree[] = [];
    currentFolder: TreeTableTree;

    ngOnInit() {
        this.currentFolder = this.treeTableData.root;
        this.path.push(this.currentFolder);
    }

    chdir(dir: string): boolean {
        if (dir === "..") {
            if (this.path.length > 1) {
                this.path.pop();
                this.currentFolder = this.path[this.path.length - 1];
            }
            return;
        }
        for (var key in this.currentFolder.children) {
            if (this.currentFolder.children[key].content === dir) {
                var newFolder = this.currentFolder.children[key];
                this.path.push(newFolder);
                this.currentFolder = newFolder;
                return;
            }
        }
    };

    setDir(dir: TreeTableTree): void {
        while ((this.path.length > 1)
            && (this.path[this.path.length - 1] !== dir)) {
            this.path.pop();
        }
        this.currentFolder = this.path[this.path.length - 1];
    }

}