var analysisUIModule: angular.IModule = angular.module("analysisUIModule", ["projectManagerModule"]);

analysisUIModule.controller("analysisBrowserCtrl", ["$scope", "$routeParams", "$filter", "projectManager",
    function(
        $scope: any,
        $routeParams: angular.route.IRouteParamsService,
        $filter: angular.IFilterService,
        projectManager: ProjectManager) {
        $scope.project = undefined;
        $scope.run = undefined;
        $scope.analysisFileTree = {};
        projectManager.getProject($routeParams["projectId"], function(data, status) {
            $scope.project = data;
            projectManager.getAnalysisFileTree(
                $scope.project.information.projectId,
                $routeParams["runId"],
                function(data, status) {
                    var treeTableData: TreeTableData = new TreeTableData();
                    treeTableData.root = convertAnalysisFileTree(data, null, $filter);
                    treeTableData.columnHeaders.push(
                        new TreeTableColumnHeader("Name", "Name of file or folder"));
                    treeTableData.columnHeaders.push(
                        new TreeTableColumnHeader("Size", "Size of file or size of folder without sub folders."));
                    treeTableData.columnHeaders.push(
                        new TreeTableColumnHeader("Size Recursive", "Size of file or size of folder including sub folders."));
                    treeTableData.columnHeaders.push(
                        new TreeTableColumnHeader("Analyzes", "Successful analyzes."));
                    $scope.analysisFileTree = treeTableData;
                },
                function(data, status, error) { }
                );
            projectManager.getRun($routeParams["projectId"], $routeParams["runId"],
                function(data, status) {
                    $scope.run = data;
                },
                function(data, status, error) { });
        }, function(data, status, error) {
            });
    }]);


function convertAnalysisFileTree(
    fileTree: any,
    parent: TreeTableTree,
    $filter: angular.IFilterService)
    : TreeTableTree {
    var treeTableData: TreeTableTree = new TreeTableTree(parent);
    treeTableData.content = fileTree.name;
    treeTableData.id = fileTree.hashId.algorithmName + ":" + fileTree.hashId.hash;
    var analyses = "";
    fileTree.analyzedCodes.forEach(function(analysis) {
        if (analyses.length > 0) {
            analyses += ", ";
        }
        analyses += analysis.languageName + " " + analysis.languageVersion;
    });
    treeTableData.addColumn(new TreeTableColumn($filter("fsSize")(fileTree.size), null, null));
    treeTableData.addColumn(new TreeTableColumn($filter("fsSize")(fileTree.sizeRecursive), null, null));
    treeTableData.addColumn(new TreeTableColumn(analyses, null, "/file.html#/summary/" + treeTableData.id));
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
            treeTableData.addChild(convertAnalysisFileTree(child, treeTableData, $filter));
        });
        treeTableData.imageUrl = "images/icons/FatCow_Icons16x16/folder.png";
    } else {
        treeTableData.imageUrl = "images/icons/FatCow_Icons16x16/document_green.png";
        treeTableData.link = "/file.html#/summary/" + treeTableData.id;
    }
    return treeTableData;
}

analysisUIModule.controller("runListCtrl", ["$scope", "$routeParams", "projectManager",
    function(
        $scope: any,
        $routeParams: angular.route.IRouteParamsService,
        projectManager: ProjectManager) {
        $scope.project = undefined;
        $scope.runs = undefined;
        projectManager.getProject($routeParams["projectId"],
            function(data, status) {
                $scope.project = data;
            },
            function(data, status, error) {
            }
            );
        projectManager.readAllRunInformation($routeParams["projectId"],
            function(data, status) {
                $scope.runs = data;
            },
            function(data, status, error) {
            }
            );
    }]);

/**
 * This controller needs the :projectId route parameter set.
 */
analysisUIModule.controller("lastRunCtrl", ["$scope", "$routeParams", "projectManager",
    function(
        $scope: any,
        $routeParams: angular.route.IRouteParamsService,
        projectManager: ProjectManager) {
        $scope.lastRun = undefined;
        projectManager.getLastRun($routeParams["projectId"],
            function(data, status) {
                $scope.lastRun = data;
            },
            function(data, status, error) {
            }
            );
    }]);
    