import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {DefaultDatePipe} from '../../commons/pipes/default-date.pipe';
import {DurationPipe} from '../../commons/pipes/duration.pipe';
import {HashIdPipe} from '../../commons/pipes/hash-id.pipe';
import {VersionPipe} from '../../commons/pipes/version.pipe';
import {FsSizePipe} from '../../commons/pipes/fs-size.pipe';
import {Utilities} from '../../commons/Utilities';
import {Project} from '../../commons/domain/Project';
import {TableColumnHeader} from '../../commons/tables/TableColumnHeader';
import {TableCell} from '../../commons/tables/TableCell';
import {TreeTableData} from '../../commons/tables/TreeTableData';
import {TreeTableNode} from '../../commons/tables/TreeTableNode';
import {ProjectRunMenuComponent} from './project-run-menu.component';
import {ProjectManager} from '../../commons/purifinity/ProjectManager';
import {AnalysisStore} from '../../commons/purifinity/AnalysisStore';
import {ProgressIndicatorComponent} from '../../components/progress-indicator.component';

import {TabSetComponent} from '../../components/tabs/tabset.component';
import {TabComponent} from '../../components/tabs/tab.component';
import {TreeTableComponent} from '../../components/tables/tree-table.component';

@Component({
    selector: 'project-run-analysis',
    directives: [
        ProjectRunMenuComponent,
        ProgressIndicatorComponent,
        TabSetComponent,
        TabComponent,
        TreeTableComponent
    ],
    pipes: [
        DefaultDatePipe,
        DurationPipe,
        HashIdPipe,
        VersionPipe
    ],
    templateUrl: '../../html/project/run/analysis.html'
})
export class ProjectRunAnalysisComponent {

    private fsSizePipe: FsSizePipe = new FsSizePipe();

    private projectId: string;
    private runId: string;

    private project: Project;
    private run = undefined;
    analysisFileTree: TreeTableData;
    private hashIds: { [hashId: string]: string[] } = {};
    preAnalysisOutput: string = "";

    constructor(private routeParams: RouteParams, private projectManager: ProjectManager, private analysisStore: AnalysisStore) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');

        let component = this;
        projectManager.getProject(this.projectId, function(project: Project) {
            component.project = project;
            projectManager.getAnalysisFileTree(
                component.projectId,
                component.runId,
                function(data, status) {
                    let root = component.convertAnalysisFileTree(data, null);
                    let columnHeaders: TableColumnHeader[] = [];
                    columnHeaders.push(
                        new TableColumnHeader("Name", "Name of file or folder"));
                    columnHeaders.push(
                        new TableColumnHeader("Size", "Size of file or size of folder without sub folders."));
                    columnHeaders.push(
                        new TableColumnHeader("Size Recursive", "Size of file or size of folder including sub folders."));
                    columnHeaders.push(
                        new TableColumnHeader("Analyzes", "Successful analyzes."));
                    component.analysisFileTree = new TreeTableData(columnHeaders, root);
                    projectManager.getRun(component.projectId, component.runId,
                        function(data, status) {
                            component.run = data;
                            let hashIds: { [hashId: string]: string[] } = {};
                            component.hashIds = hashIds;
                            component.collectHashIds(hashIds, data.fileTree, "/");
                        },
                        function(response: Response) { });
                },
                function(response: Response) { }
            );
        }, function(response: Response) {
        });
        analysisStore.getPreAnalysisScriptOutput(this.projectId, this.runId,
            function(output: string) {
                component.preAnalysisOutput = output;
            },
            function(response: Response) { });
    }

    convertAnalysisFileTree(
        fileTree: any,
        parent: TreeTableNode)
        : TreeTableNode {
        let component = this;
        let treeTableTree: TreeTableNode = new TreeTableNode(parent);
        treeTableTree.content = fileTree.name;
        treeTableTree.id = fileTree.hashId.algorithmName + ":" + fileTree.hashId.hash;
        let analyses = "";
        fileTree.analyzedCodes.forEach(function(analysis) {
            if (analyses.length > 0) {
                analyses += ", ";
            }
            analyses += analysis.languageName + " " + analysis.languageVersion;
        });
        treeTableTree.addColumn(new TableCell(this.fsSizePipe.transform(fileTree.size, []), null, null));
        treeTableTree.addColumn(new TableCell(this.fsSizePipe.transform(fileTree.sizeRecursive, []), null, null));
        treeTableTree.addColumn(new TableCell(analyses, null, "/file.html#/summary/" + treeTableTree.id));
        if (fileTree.children.length > 0) {
            treeTableTree.children = [];
            fileTree.children.sort(function(l, r) {
                if ((l.children) && (l.children.length > 0)) {
                    if ((r.children) && (r.children.length > 0)) {
                        return Utilities.strcmp(l.name.toUpperCase(), r.name.toUpperCase());
                    }
                    return -1;
                } else {
                    if ((r.children) && (r.children.length > 0)) {
                        return 1;
                    }
                    return Utilities.strcmp(l.name.toUpperCase(), r.name.toUpperCase());
                }
            });
            fileTree.children.forEach(function(child) {
                let tree: TreeTableNode = component.convertAnalysisFileTree(child, treeTableTree);
                treeTableTree.addChild(tree);
            });
            treeTableTree.imageUrl = "images/icons/FatCow_Icons16x16/folder.png";
        } else {
            treeTableTree.imageUrl = "images/icons/FatCow_Icons16x16/document_green.png";
            treeTableTree.routerLink = ['FileSummary', { hashId: treeTableTree.id }];
        }
        return treeTableTree;
    }

    collectHashIds(hashIds: { [hashId: string]: string[] }, node: any, path: string): void {
        let hashId = node.hashId.algorithmName + ":" + node.hashId.hash;
        if (!hashIds[hashId]) {
            hashIds[hashId] = [];
        }
        hashIds[hashId].push(path + node.name);
        if ((!node.file) && (node.children.length > 0)) {
            let component = this;
            node.children.forEach(function(child: any) {
                component.collectHashIds(hashIds, child, path + node.name + "/");
            });
        }
    }

    isLoading(): boolean {
        return !(this.project && this.run && this.analysisFileTree);
    }
}
