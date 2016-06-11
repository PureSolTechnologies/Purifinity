import {Component} from 'angular2/core';
import {RouteParams} from 'angular2/router';
import {Response} from 'angular2/http';

import {TabSetComponent} from '../../components/tabs/tabset.component';
import {TabComponent} from '../../components/tabs/tab.component';
import {MetricsEvaluatorSelectionComponent} from '../../components/evaluation/metrics-evaluator-selection.component';
import {ParetoChartComponent} from '../../components/charts/pareto-chart.component';
import {VerticalParetoChartComponent} from '../../components/charts/vertical-pareto-chart.component';
import {HistogramChartComponent} from '../../components/charts/histogram-chart.component';
import {TreeMapComponent} from '../../components/charts/tree-map.component';
import {CummulativeDistributionChartComponent} from '../../components/charts/cummulative-distribution-chart.component';
import {TreeTableComponent} from '../../components/treetable/tree-table.component';

import {ParetoDatum} from '../../commons/charts/ParetoDatum';
import {EvaluatorServiceInformation} from '../../commons/plugins/EvaluatorServiceInformation';
import {MetricValuePipe} from '../../commons/pipes/metric-value.pipe';
import {Utilities} from '../../commons/Utilities';
import {Project} from '../../commons/domain/Project';
import {ProjectManager} from '../../commons/purifinity/ProjectManager';
import {EvaluatorStore} from '../../commons/purifinity/EvaluatorStore';
import {ProjectRunMenuComponent} from './project-run-menu.component';
import {TreeTableNode} from '../../commons/treetable/TreeTableNode';
import {TreeTableData} from '../../commons/treetable/TreeTableData';
import {TableColumnHeader} from '../../commons/tables/TableColumnHeader';
import {TableCell} from '../../commons/tables/TableCell';

@Component({
    selector: 'project-run-metrics',
    directives: [
        TabSetComponent,
        TabComponent,
        TreeTableComponent,
        ProjectRunMenuComponent,
        MetricsEvaluatorSelectionComponent,
        ParetoChartComponent,
        VerticalParetoChartComponent,
        HistogramChartComponent,
        TreeMapComponent,
        CummulativeDistributionChartComponent
    ],
    pipes: [
        MetricValuePipe
    ],
    templateUrl: '../../html/project/run/metrics.html'
})
export class ProjectRunMetricsComponent {

    private projectId: string;
    private runId: string;
    private evaluator: EvaluatorServiceInformation = undefined;
    private codeRangeType: string = "";
    private parameter: string = "";
    private fileTree = undefined;
    metricsTreeTable: TreeTableData;
    private metrics: any = {};
    private project: Project = undefined;
    private run: any = undefined;
    private codeRangeTypes = [];
    private paretoData: ParetoDatum[] = [];
    private mapData =
    {
        "name": "flare",
        "children": [
            {
                "name": "analytics",
                "children": [
                    {
                        "name": "cluster",
                        "children": [
                            { "name": "AgglomerativeCluster", "size": 3938 },
                            { "name": "CommunityStructure", "size": 3812 },
                            { "name": "MergeEdge", "size": 743 }
                        ]
                    },
                    {
                        "name": "graph",
                        "children": [
                            { "name": "BetweennessCentrality", "size": 3534 },
                            { "name": "LinkDistance", "size": 5731 }
                        ]
                    }
                ]
            }
        ]
    };


    constructor(private routeParams: RouteParams, private projectManager: ProjectManager, private evaluatorStore: EvaluatorStore) {
        this.projectId = routeParams.get('projectId');
        this.runId = routeParams.get('runId');
    }

    ngOnInit() {
        let component = this;
        this.projectManager.getProject(this.projectId, function(project: Project) {
            component.project = project;
            component.projectManager.getAnalysisFileTree(
                component.projectId,
                component.runId,
                function(data, status) {
                    component.fileTree = data;
                    let root: TreeTableNode = component.convertFileTreeForMetrics(data, null);
                    let columnHeaders: TableColumnHeader[] = [];
                    columnHeaders.push(new TableColumnHeader("Name", "Name of file or folder"));
                    component.metricsTreeTable = new TreeTableData(columnHeaders, root);
                },
                function(response: Response) {
                }
            );
        }, function(response: Response) {
        });
    }

    setEvaluator(evaluator: EvaluatorServiceInformation) {
        if (evaluator === this.evaluator) {
            return;
        }
        this.evaluator = evaluator;
        this.paretoData = [];
        this.codeRangeType = undefined;
        this.parameter = undefined;
        if (!(this.project.information && evaluator)) {
            return;
        }
        let component = this;
        this.evaluatorStore.getRunMetrics(this.projectId, this.runId, evaluator.id, function(data: any) {
            let types = [];
            types.push("DIRECTORY");
            types.push("FILE");
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
            component.applyMetricsToFileTree(component.metricsTreeTable.root, component.metrics, component.metrics.parameters);
            component.metricsTreeTable.columnHeaders = [{ name: "Name", tooltip: "Name of file or folder" }];
            component.metrics.parameters.forEach(function(parameter) {
                let name = parameter.name;
                if (parameter.unit) {
                    name += " [" + parameter.unit + "]";
                }
                component.metricsTreeTable.columnHeaders.push({ name: name, tooltip: parameter.description });
            });
        }, function(response: Response) {
        }
        );
    }

    changedValue(newValue) {
        this.recalculateChartData();
    }

    recalculateChartData(): void {
        if ((!this.codeRangeType) || (!this.parameter)) {
            this.paretoData = [];
            return;
        }
        let paretoData = [];
        if (this.codeRangeType === "DIRECTORY") {
            for (let hashId in this.metrics.directoryMetrics) {
                let metric = this.metrics.directoryMetrics[hashId];
                let directory = this.fileTree.getDirectory(hashId);
                for (let valueName in metric.values) {
                    let value = metric.values[valueName];
                    if (!paretoData[value.parameter.name]) {
                        paretoData[value.parameter.name] = [];
                    }
                    paretoData[value.parameter.name].push(new ParetoDatum(directory.name + ":" + value.parameter.name, value.value));
                }
            }
        } else {
            let component = this;
            for (let hashId in this.metrics.fileMetrics) {
                let codeRangeMetrics = this.metrics.fileMetrics[hashId].codeRangeMetrics;
                let file = this.fileTree.getFile(hashId);
                if (codeRangeMetrics) {
                    codeRangeMetrics.forEach(function(metric) {
                        if (metric.codeRangeType === component.codeRangeType) {
                            for (let valueName in metric.values) {
                                let value = metric.values[valueName];
                                if (!paretoData[value.parameter.name]) {
                                    paretoData[value.parameter.name] = [];
                                }
                                paretoData[value.parameter.name].push({ name: file.name + ":" + metric.codeRangeName, value: value.value });
                            }
                        }
                    });
                }
            }
        }
        for (let key in paretoData) {
            paretoData[key].sort(function(l, r) {
                return -1 * (l.value - r.value);
            });
        }
        this.paretoData = paretoData;
    }

    showClick(item: any): void {
        alert(item);
    }

    convertFileTreeForMetrics(
        fileTree: any,
        parent: TreeTableNode)
        : TreeTableNode {
        let component = this;
        let treeTableData: TreeTableNode = new TreeTableNode(parent);
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

    getProjectMetric(parameter): number {
        let hashidString: string = this.fileTree.hashId.algorithmName + ":" + this.fileTree.hashId.hash;
        let directoryMetrics = this.metrics.directoryMetrics[hashidString];
        let value = directoryMetrics.values[parameter.name];
        if (!value) {
            return null;
        }
        return value.value;
    }
}
