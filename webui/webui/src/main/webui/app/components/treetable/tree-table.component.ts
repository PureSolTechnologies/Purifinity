import {Component, Input} from 'angular2/core';
import {ROUTER_DIRECTIVES} from 'angular2/router';

import {TreeTableData} from '../../commons/treetable/TreeTableData';
import {TreeTableNode} from '../../commons/treetable/TreeTableNode';

@Component({
    selector: 'tree-table',
    directives: [
        ROUTER_DIRECTIVES
    ],
    templateUrl: '../../../html/components/treetable/tree-table.html'
})
export class TreeTableComponent {

    @Input()
    private treeTableData: TreeTableData;

    path: TreeTableNode[] = [];
    currentFolder: TreeTableNode;

    constructor(){}
    
    ngOnInit() {
        if (this.treeTableData) {
            this.currentFolder = this.treeTableData.root;
            this.path = [];
            this.path.push(this.currentFolder);
        } else {
            this.currentFolder = undefined;
            this.path = [];
        }
    }

    ngOnChanges(newValues) {
        if (newValues.treeTableData.currentValue) {
            this.treeTableData = newValues.treeTableData.currentValue;
            this.currentFolder = this.treeTableData.root;
            this.path = [];
            this.path.push(this.currentFolder);
        } else {
            this.currentFolder = undefined;
            this.path = [];
        }
    }

    chdir(dir: string): boolean {
        if (dir === "..") {
            if (this.path.length > 1) {
                this.path.pop();
                this.currentFolder = this.path[this.path.length - 1];
            }
            return;
        }
        if ((this.currentFolder) && (this.currentFolder.children)) {
            for (var key in this.currentFolder.children) {
                if (this.currentFolder.children[key].content === dir) {
                    var newFolder = this.currentFolder.children[key];
                    this.path.push(newFolder);
                    this.currentFolder = newFolder;
                    return;
                }
            }
        }
    };

    setDir(dir: TreeTableNode): void {
        while ((this.path.length > 1)
            && (this.path[this.path.length - 1] !== dir)) {
            this.path.pop();
        }
        this.currentFolder = this.path[this.path.length - 1];
    }

}