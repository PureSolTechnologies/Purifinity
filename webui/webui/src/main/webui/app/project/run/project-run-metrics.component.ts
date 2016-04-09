import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {EvaluatorSelectionComponent} from '../../components/evaluation/evaluator-selection.component';

import {MetricValuePipe} from '../../commons/pipes/metric-value.pipe';
import {Utilities} from '../../commons/Utilities';
import {Project} from '../../commons/domain/Project';
import {ProjectManager} from '../../commons/purifinity/ProjectManager';
import {PurifinityServerConnector} from '../../commons/purifinity/PurifinityServerConnector';
import {ProjectRunMenuComponent} from './project-run-menu.component';
import {TreeTableTree} from '../../commons/treetable/TreeTableTree';
import {TreeTableData} from '../../commons/treetable/TreeTableData';
import {TableColumnHeader} from '../../commons/tables/TableColumnHeader';
import {TableCell} from '../../commons/tables/TableCell';

@Component({
    selector: 'project-run-metrics',
    directives: [
        ProjectRunMenuComponent,
        EvaluatorSelectionComponent
    ],
    pipes: [
        MetricValuePipe
    ],
    templateUrl: '../../html/project/run/metrics.html'
})
export class ProjectRunMetricsComponent {

    private projectId: string;
    private runId: string;
    private selectedEvaluator = undefined;
    private selection = {
        codeRangeType: undefined,
        parameter: undefined
    };
    private fileTree = undefined;
    private metricsTreeTable: TreeTableData = new TreeTableData([], null);
    private metrics: any = {};
    private project: Project = undefined;
    private run: any = undefined;
    private codeRangeTypes = [];
    private paretoData = [];


    constructor(private routeParams: RouteParams, private projectManager: ProjectManager, private purifinityServerConnector: PurifinityServerConnector) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');

        let component = this;
        projectManager.getProject(this.projectId, function(project: Project) {
            component.project = project;
            projectManager.getRun(component.projectId, component.runId, function(data, status) {
                component.run = data;
                projectManager.getAnalysisFileTree(
                    component.projectId,
                    component.runId,
                    function(data, status) {
                        component.fileTree = data;
                        let root: TreeTableTree = this.convertFileTreeForMetrics(data, null);
                        let columnHeaders = new Array<TableColumnHeader>();
                        columnHeaders.push(new TableColumnHeader("Name", "Name of file or folder"));
                        component.metricsTreeTable = new TreeTableData(columnHeaders, root);
                    },
                    function(response: Response) {
                    }
                );
            }, function(response: Response) {
            });
        }, function(response: Response) { }
        );
    }

    setSelectedEvaluator(newValue) {
        this.paretoData = [];
        this.selection.codeRangeType = undefined;
        this.selection.parameter = undefined;
        if (newValue === this.selectedEvaluator) {
            return;
        }
        if (this.project.information && this.run.information.runId && newValue) {
            let component = this;
            this.purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/metrics/"
                + component.project.information.projectId
                + "/" + component.run.information.runId
                + "/" + newValue, function(response: Response) {
                    let types = [];
                    types.push("DIRECTORY");
                    types.push("FILE");
                    let data = response.json();
                    for (let hashid in data.fileMetrics) {
                        let fileResults = data.fileMetrics[hashid];
                        fileResults.codeRangeMetrics.forEach(function(metrics) {
                            if (types.indexOf(metrics.codeRangeType) < 0) {
                                types.push(metrics.codeRangeType);
                            }
                        });
                    }
                    component.codeRangeTypes = [];
                    types.forEach(function(type) {
                        component.codeRangeTypes.push({ name: type });
                    });
                    component.codeRangeTypes.sort();
                    component.metrics = data;
                    this.applyMetricsToFileTree(component.metricsTreeTable.root, component.metrics, component.metrics.parameters);
                    component.metricsTreeTable.columnHeaders = [{ name: "Name", tooltip: "Name of file or folder" }];
                    component.metrics.parameters.forEach(function(parameter) {
                        let name = parameter.name;
                        if (parameter.unit) {
                            name += " [" + parameter.unit + "]";
                        }
                        component.metricsTreeTable.columnHeaders.push({ name: name, tooltip: parameter.description });
                    });
                }, function(response: Response) {
                });
        }
    }

    changedValue(newValue) {
        this.recalculateChartData();
    }

    recalculateChartData(): void {
        if ((!this.selection.codeRangeType) || (!this.selection.parameter)) {
            this.paretoData = [];
            return;
        }
        let newData = [];
        if (this.selection.codeRangeType === "DIRECTORY") {
            for (let hashId in this.metrics.directoryMetrics) {
                let metric = this.metrics.directoryMetrics[hashId];
                let directory = this.fileTree.getDirectory(hashId);
                for (let valueName in metric.values) {
                    let value = metric.values[valueName];
                    if (!newData[value.parameter.name]) {
                        newData[value.parameter.name] = [];
                    }
                    newData[value.parameter.name].push({ name: directory.name + ":" + value.parameter.name, value: value.value });
                }
            }
        } else {
            for (let hashId in this.metrics.fileMetrics) {
                let codeRangeMetrics = this.metrics.fileMetrics[hashId].codeRangeMetrics;
                let file = this.fileTree.getFile(hashId);
                if (codeRangeMetrics) {
                    codeRangeMetrics.forEach(function(metric) {
                        if (metric.codeRangeType === this.selection.codeRangeType) {
                            for (let valueName in metric.values) {
                                let value = metric.values[valueName];
                                if (!newData[value.parameter.name]) {
                                    newData[value.parameter.name] = [];
                                }
                                newData[value.parameter.name].push({ name: file.name + ":" + metric.codeRangeName, value: value.value });
                            }
                        }
                    });
                }
            }
        }
        for (let key in newData) {
            newData[key].sort(function(l, r) {
                return -1 * (l.value - r.value);
            });
        }
        this.paretoData = newData;
    }

    showClick(item: any): void {
        alert(item);
    }

    convertFileTreeForMetrics(
        fileTree: any,
        parent: TreeTableTree)
        : TreeTableTree {
        let component = this;
        let treeTableData: TreeTableTree = new TreeTableTree(parent);
        treeTableData.content = fileTree.name;
        treeTableData.id = fileTree.hashId.algorithmName + ":" + fileTree.hashId.hash;
        treeTableData.columns = [];
        if (fileTree.children.length > 0) {
            treeTableData.children = [];
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
                treeTableData.addChild(component.convertFileTreeForMetrics(child, treeTableData));
            });
            treeTableData.imageUrl = "images/icons/FatCow_Icons16x16/folder.png";
        } else {
            treeTableData.imageUrl = "images/icons/FatCow_Icons16x16/document_green.png";
        }
        return treeTableData;
    }

    applyMetricsToFileTree(treeTableData, runMetrics, parameters): void {
        let component = this;
        let valueFilter = new MetricValuePipe();
        let found = false;
        treeTableData.columns = [];
        if (treeTableData.children && (treeTableData.children.length > 0)) {
            let directoryMetrics = runMetrics.directoryMetrics[treeTableData.id];
            if (directoryMetrics) {
                found = true;
                parameters.forEach(function(parameter) {
                    let value = directoryMetrics.values[parameter.name];
                    if (value) {
                        treeTableData.addColumn(new TableCell(valueFilter.transform(value.value, []), undefined, undefined));
                    } else {
                        treeTableData.addColumn(new TableCell("n/a", undefined, undefined));
                    }
                });
            }
            treeTableData.children.forEach(function(child) {
                component.applyMetricsToFileTree(child, runMetrics, parameters);
            });
        } else {
            let fileMetrics = runMetrics.fileMetrics[treeTableData.id];
            if (fileMetrics) {
                fileMetrics.codeRangeMetrics.forEach(function(metric) {
                    if (metric.codeRangeType === "FILE") {
                        found = true;
                        parameters.forEach(function(parameter) {
                            let value = metric.values[parameter.name];
                            if (value) {
                                treeTableData.addColumn(new TableCell(valueFilter.transform(value.value, []), null, null));
                            } else {
                                treeTableData.addColumn(new TableCell("n/a", null, null));
                            }
                        });
                    }
                });
            }
        }
        if (!found) {
            parameters.forEach(function(parameter) {
                treeTableData.columns.push({ content: "" });
            });
        }
    }

}
