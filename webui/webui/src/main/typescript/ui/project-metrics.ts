var projectMetricsModule: angular.IModule = angular.module("projectMetricsModule", [
    "projectManagerModule", "pluginManagerModule", "purifinityServerModule", "purifinityUI"
]);

projectMetricsModule.controller("fileSystemMetrics", ["$scope", "$routeParams", "$filter", "projectManager", "purifinityServerConnector", function($scope, $routeParams, $filter, projectManager, purifinityServerConnector) {
    $scope.selectedEvaluator = undefined;
    $scope.selection = {
        codeRangeType: undefined,
        parameter: undefined
    };
    $scope.fileTree = undefined;
    $scope.metricsTreeTable = {};
    $scope.metrics = {};
    $scope.project = {};
    $scope.run = {};
    $scope.codeRangeTypes = [];
    $scope.paretoData = [];
    projectManager.getProject($routeParams.projectId, function(data, status) {
        $scope.project = data;
        projectManager.getLastRun($routeParams.projectId, function(data, status) {
            $scope.run = data;
            projectManager.getAnalysisFileTree(
                $scope.project.information.projectId,
                $scope.run.runId,
                function(data, status) {
                    $scope.fileTree = data;
                    var treeTableData: TreeTableData = new TreeTableData();
                    treeTableData.root = convertFileTreeForMetrics(data, null);
                    treeTableData.columnHeaders.push(new TreeTableColumnHeader("Name", "Name of file or folder"));
                    $scope.metricsTreeTable = treeTableData;
                },
                function(data, status, error) {
                }
                );
        }, function(data, status, error) {
            });
    }, function(data, status, error) { }
        );
    $scope.$watch("selectedEvaluator", function(newValue, oldValue) {
        $scope.paretoData = [];
        $scope.selection.codeRangeType = undefined;
        $scope.selection.parameter = undefined;
        if (newValue === oldValue) {
            return;
        }
        if ($scope.project.information && $scope.run.runId && newValue) {
            purifinityServerConnector.get("/purifinityserver/rest/evaluatorstore/metrics/"
                + $scope.project.information.projectId
                + "/" + $scope.run.runId
                + "/" + newValue, function(data, status) {
                    var types = [];
                    types.push("DIRECTORY");
                    types.push("FILE");
                    for (var hashid in data.fileMetrics) {
                        var fileResults = data.fileMetrics[hashid];
                        fileResults.codeRangeMetrics.forEach(function(metrics) {
                            if (types.indexOf(metrics.codeRangeType) < 0) {
                                types.push(metrics.codeRangeType);
                            }
                        });
                    }
                    $scope.codeRangeTypes = [];
                    types.forEach(function(type) {
                        $scope.codeRangeTypes.push({ name: type });
                    });
                    $scope.codeRangeTypes.sort();
                    $scope.selectedCodeRangeType = [];
                    $scope.metrics = data;
                    applyMetricsToFileTree($scope.metricsTreeTable.root, $scope.metrics, $scope.metrics.parameters, $filter);
                    $scope.metricsTreeTable.columnHeaders = [{ name: "Name", tooltip: "Name of file or folder" }];
                    $scope.metrics.parameters.forEach(function(parameter) {
                        var name = parameter.name;
                        if (parameter.unit) {
                            name += " [" + parameter.unit + "]";
                        }
                        $scope.metricsTreeTable.columnHeaders.push({ name: name, tooltip: parameter.description });
                    });
                }, function(data, status, error) {
                });
        }
    }, true);
    $scope.recalculateChartData = function() {
        if ((!$scope.selection.codeRangeType) || (!$scope.selection.parameter)) {
            $scope.paretoData = [];
            return;
        }
        var newData = [];
        if ($scope.selection.codeRangeType === "DIRECTORY") {
            for (var hashId in $scope.metrics.directoryMetrics) {
                var metric = $scope.metrics.directoryMetrics[hashId];
                var directory = $scope.fileTree.getDirectory(hashId);
                for (var valueName in metric.values) {
                    var value = metric.values[valueName];
                    if (!newData[value.parameter.name]) {
                        newData[value.parameter.name] = [];
                    }
                    newData[value.parameter.name].push({ name: directory.name + ":" + value.parameter.name, value: value.value });
                }
            }
        } else {
            for (var hashId in $scope.metrics.fileMetrics) {
                var codeRangeMetrics = $scope.metrics.fileMetrics[hashId].codeRangeMetrics;
                var file = $scope.fileTree.getFile(hashId);
                if (codeRangeMetrics) {
                    codeRangeMetrics.forEach(function(metric) {
                        if (metric.codeRangeType === $scope.selection.codeRangeType) {
                            for (var valueName in metric.values) {
                                var value = metric.values[valueName];
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
        for (var key in newData) {
            newData[key].sort(function(l, r) {
                return -1 * (l.value - r.value);
            });
        }
        $scope.paretoData = newData;
    };
    $scope.$watchGroup(["selection.codeRangeType", "selection.parameter"], function(newValue, oldValue) {
        if (newValue === oldValue) {
            return;
        }
        $scope.recalculateChartData();
    }, true);
    $scope.showClick = function(item) {
        alert(item);
    };
}]);

function convertFileTreeForMetrics(fileTree: any, parent: TreeTableTree): TreeTableTree {
    var treeTableData: TreeTableTree = new TreeTableTree(parent);
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
            treeTableData.addChild(convertFileTreeForMetrics(child, treeTableData));
        });
        treeTableData.imageUrl = "images/icons/FatCow_Icons16x16/folder.png";
    } else {
        treeTableData.imageUrl = "images/icons/FatCow_Icons16x16/document_green.png";
    }
    return treeTableData;
}

function applyMetricsToFileTree(treeTableData, runMetrics, parameters, filter) {
    var valueFilter = filter("metricValue");
    var found = false;
    treeTableData.columns = [];
    if (treeTableData.children && (treeTableData.children.length > 0)) {
        var directoryMetrics = runMetrics.directoryMetrics[treeTableData.id];
        if (directoryMetrics) {
            found = true;
            parameters.forEach(function(parameter) {
                var value = directoryMetrics.values[parameter.name];
                if (value) {
                    treeTableData.addColumn(new TreeTableColumn(valueFilter(value.value), undefined, undefined));
                } else {
                    treeTableData.addColumn(new TreeTableColumn("n/a", undefined, undefined));
                }
            });
        }
        treeTableData.children.forEach(function(child) {
            applyMetricsToFileTree(child, runMetrics, parameters, filter);
        });
    } else {
        var fileMetrics = runMetrics.fileMetrics[treeTableData.id];
        if (fileMetrics) {
            fileMetrics.codeRangeMetrics.forEach(function(metric) {
                if (metric.codeRangeType === "FILE") {
                    found = true;
                    parameters.forEach(function(parameter) {
                        var value = metric.values[parameter.name];
                        if (value) {
                            treeTableData.addColumn(new TreeTableColumn(valueFilter(value.value), null, null));
                        } else {
                            treeTableData.addColumn(new TreeTableColumn("n/a", null, null));
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

projectMetricsModule.controller("treeMapCtrl", ["$scope",
    function treeMapCtrl($scope) {
        // Treemap test!
        $scope.mapData =
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
    }]);
