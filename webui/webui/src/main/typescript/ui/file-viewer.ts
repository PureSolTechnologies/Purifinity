var fileViewerModule: angular.IModule = angular
    .module("fileViewerModule", ["fileStoreModule"]);

fileViewerModule.controller("fileSummaryCtrl", ["$scope", "$routeParams", "fileStore",
    function(
        $scope: any,
        $routeParams: angular.route.IRouteParamsService,
        fileStore: FileStore) {
        $scope.analyses = {};
        $scope.sourceCode = {};
        fileStore.wasAnalyzed($routeParams["hashId"], function(data, status) {
            if (data) {
                fileStore.loadAnalyses($routeParams["hashId"], function(data, status) {
                    $scope.analyses = data;
                }, function(data, status, error) {
                    });
            }
        }, function(data, status, error) {
            });
        fileStore.readSourceCode($routeParams["hashId"], function(data, status) {
            $scope.sourceCode = data;
        }, function(data, status, error) {
            });
    }]);


fileViewerModule.directive("sourceCode",
    function() {
        return {
            restrict: "E",
            scope: {
                sourceCodeData: "=ngModel"
            },
            controller: ['$scope', function($scope: any) {
            }],
            templateUrl: "directives/source-code.html"
        };
    });



fileViewerModule.controller("fileAnalysisCtrl", ["$scope", "$routeParams",
    "$filter", "fileStore",
    function fileAnalysisCtrl($scope, $routeParams, $filter, fileStore) {
        $scope.analyzerSelection = undefined;
        $scope.analyses = [];
        $scope.usts = {};
        $scope.selectedUST = undefined;
        fileStore
            .wasAnalyzed(
            $routeParams.hashId,
            function(data, status) {
                if (data) {
                    fileStore
                        .loadAnalyses(
                        $routeParams.hashId,
                        function(data, status) {
                            $scope.analyses = data;
                            $scope.analyses
                                .forEach(function(
                                analysis) {
                                var id = analysis.analysisInformation.analyzerId
                                    + $filter(
                                        "version")
                                        (
                                        analysis.analysisInformation.analyzerVersion);
                                analysis.analyzableCodeRanges
                                    .forEach(function(
                                    codeRange) {
                                    if (codeRange.type === "FILE") {
                                        $scope.usts[id] = codeRange.ust;
                                    }
                                });

                            });
                        }, function(data, status, error) {
                        });
                }
            }, function(data, status, error) {
            });
        $scope.$watch("analyzerSelection", function(newValue, oldValue) {
            $scope.selectedUST = $scope.usts[newValue];
        });
    }]);

fileViewerModule.directive("ustView",
    function ustView() {
        return {
            restrict: "E",
            replace: false,
            scope: {
                ustViewData: "=ngModel"
            },
            controller: ['$scope', function ustViewCtrl($scope) {
            }],
            link: function(scope: any, element, attrs) {
                element.append($("<div/>").text("Hallo").append(
                    $("<span/>").text(scope.ustViewData)));
            }
        };
    });
